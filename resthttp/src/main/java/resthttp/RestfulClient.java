package resthttp;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.jdeferred.Deferred;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import java.io.IOException;

import resthttp.execption.ClientInputException;
import resthttp.execption.ServerException;

/**
 * Created by allovince on 15/8/17.
 */
public class RestfulClient {
    private static OkHttpClient httpClient = new OkHttpClient();

    private static FailCallback serverFailedHandler = null;

    public static String test() {
        return "world";
    }
    public static void registerServerFailedHandler(FailCallback handler) {
        serverFailedHandler = handler;
    }

    public static FailCallback getServerFaileHandler() {
        if (null == serverFailedHandler) {
            return serverFailedHandler = new FailCallback() {
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

    private static Promise callByRequest(final Request request) {
        final Deferred deferred = new DeferredObject();
        Promise promise = deferred.promise();
        httpClient.newCall(request).enqueue(new Callback() {
            public void onFailure(Request request, IOException e) {
                deferred.reject(e);
            }

            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    deferred.resolve(response);
                    return;
                }

                int statusCode = response.code();
                if (statusCode >= 400 && statusCode < 500) {
                    deferred.reject(new ClientInputException("Client parameters invalid", response));
                    return;
                }

                deferred.reject(new ServerException("Server problems", response));
            }
        });

        //System default handle for server error
        promise.fail(getServerFaileHandler());

        /*
        promise.fail(new FailCallback() {
            public void onFail(Object obj) {
                if (!(obj instanceof ServerException)) {
                    return;
                }
                ((ServerException) obj).printStackTrace();
            }
        });
        */

        return promise;
    }

    public static Promise promiseApiCall(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return callByRequest(request);
    }

    public static Promise promiseApiCall(String method, String url, RequestBody body) {
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

    public static Promise promiseApiCall(final Request request) {
        return callByRequest(request);
    }
}