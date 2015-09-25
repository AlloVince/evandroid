package com.evandroid.httprest;

import android.content.res.Resources;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.jdeferred.Deferred;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.evandroid.httprest.execption.*;
import com.squareup.okhttp.ResponseBody;

import okio.BufferedSource;


public class RestfulClient {
    private static OkHttpClient httpClient;

    private static FailCallback<Object> serverFailedHandler;

    public static OkHttpClient getInstance() {
        if (null == httpClient) {
            httpClient = new OkHttpClient();
            httpClient.networkInterceptors().add(new StethoInterceptor());
            httpClient.setConnectTimeout(2, TimeUnit.SECONDS);
            httpClient.setReadTimeout(2, TimeUnit.SECONDS);

            httpClient.interceptors().add(new Interceptor() {
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Log.d("okhttp", request.toString());

                    Response response = chain.proceed(request);
                    Log.d("okhttp", response.toString());
                    return response;
                }
            });

            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(cookieManager);
            httpClient.setCookieHandler(cookieManager);
        }
        return httpClient;
    }

    public static void registerServerFailedHandler(FailCallback<Object> handler) {
        serverFailedHandler = handler;
    }

    public static FailCallback<Object> getServerFailedHandler() {
        if (null == serverFailedHandler) {
            return serverFailedHandler = new FailCallback<Object>() {
                public void onFail(Object obj) {
                    if (!(obj instanceof ServerException)) {
                        return;
                    }
                    ((ServerException) obj).printStackTrace();
                }
            };
        }
        return serverFailedHandler;
    }

    private static Promise<Response, Object, Void> callByRequest(final Request request) {
        final Deferred<Response, Object, Void> deferred = new DeferredObject<>();
        Promise<Response, Object, Void> promise = deferred.promise();
        getInstance().newCall(request).enqueue(new Callback() {
            public void onFailure(Request request, IOException e) {
                deferred.reject(e);
                Log.d("okhttp", "Deferred reject triggered. " + e.toString());
            }

            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    deferred.resolve(response);
                    Log.d("okhttp", "Deferred resolve triggered. " + response.toString());
                    return;
                }

                int statusCode = response.code();
                if (statusCode >= 400 && statusCode < 500) {
                    deferred.reject(new ClientInputException("Client parameters invalid", response));
                    Log.d("okhttp", "Deferred reject triggered as ClientInput exception. " + response.toString());
                    return;
                }

                deferred.reject(new ServerException("Server problems", response));
                Log.d("okhttp", "Deferred reject triggered as ServerException. " + response.toString());
            }
        });

        //System default handle for server error
        promise.fail(getServerFailedHandler());

        return promise;
    }

    public static Promise<Response, Object, Void> promiseApiCall(InputStream resourceReader) {
        final Deferred<Response, Object, Void> deferred = new DeferredObject<>();
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            String jsonString = writer.toString();
            ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
            Request request = new Request.Builder().url("http://resource-debug/").build();
            Response response = new Response.Builder().request(request).code(200).protocol(Protocol.HTTP_1_1).body(
                    responseBody
            ).build();
            deferred.resolve(response);
        } catch (Exception e) {
            //Log.d("rest", e.toString());
            deferred.reject(e);
        }
        return deferred.promise();
    }

    public static Promise<Response, Object, Void> promiseApiCall(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return callByRequest(request);
    }

    public static Promise<Response, Object, Void> promiseApiCall(String method, String url, RequestBody body) {
        Request.Builder request = new Request.Builder();

        method = method.toUpperCase();
        switch (method) {
            case "POST":
                request.post(body);
                break;
            case "PUT":
                request.put(body);
                break;
            case "DELETE":
                request.delete(body);
                break;
        }
        return callByRequest(request.build());
    }

    public static Promise<Response, Object, Void> promiseApiCall(final Request request) {
        return callByRequest(request);
    }
}
