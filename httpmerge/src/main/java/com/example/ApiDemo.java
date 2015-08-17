package com.example;

import com.example.execption.ClientInputException;
import com.example.execption.ServerException;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jdeferred.Deferred;
import org.jdeferred.DoneCallback;
import org.jdeferred.DonePipe;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;
import org.jdeferred.multiple.MultipleResults;
import org.jdeferred.multiple.OneReject;

import java.io.IOException;
import java.util.logging.Logger;


public class ApiDemo {
    private static OkHttpClient client = new OkHttpClient();

    String syncRun(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public void asyncRun(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }
        });
    }

    public static Promise getPromise(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        final Deferred deferred = new DeferredObject();
        Promise p = deferred.promise();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                deferred.reject(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    deferred.reject(new Exception("resonse not 200"));
                    return;
                }
                deferred.resolve(response);
            }
        });
        return p;
    }

    /*
    public Promise getPolicy() {
        return getPromise("http://api.xgb.wallstcn.com/v2/policies/get/3").then(new DoneCallback() {
            public void onDone(Object response) {
                try {
                    return ((Response) response).body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */

    public static void main(String[] args) {
        RestfulApi.promiseApiCall(
                "http://api.wallstreetcn.com/v2/users/me"
                //"http://pay.wallstreetcn.com/"
        ).done(new DoneCallback() {
            @Override
            public void onDone(Object result) {
                System.out.println(result);
            }
        }).fail(new FailCallback() {
            @Override
            public void onFail(Object obj) {
                if (!(obj instanceof ClientInputException)) {
                    return;
                }
                ClientInputException exception = (ClientInputException) obj;
                exception.printStackTrace();
            }
        });

    }

    public static void main2(String[] args) throws Exception {
        ApiDemo example = new ApiDemo();

        //Sync Demo
        //String response = example.syncRun("http://api.xgb.wallstcn.com/v2/policies/get/3");
        //System.out.println(response);

        //Async Demo
        /*
        try {
            example.asyncRun("http://api.xgb.wallstcn.com/v2/policies/get/3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        final Gson gson = new Gson();

        //Promise single Demo
        getPromise(
                "http://api.xgb.wallstcn.com/v2/policies/get/1"
        ).done(new DoneCallback() {
            public void onDone(Object response) {
                try {
                    String res = ((Response) response).body().string();
                    Policy policy = gson.fromJson(res, Policy.class);

                    System.out.println(policy);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).fail(new FailCallback() {
            public void onFail(Object result) {
                System.out.println("Failed");
                ((Exception) result).printStackTrace();
            }
        });

        final DefaultDeferredManager dm = new DefaultDeferredManager();
        /*
        //Multi example
        dm.when(
                getPromise("http://api.xgb.wallstcn.com/v2/policies/get/2"),
                getPromise("http://api.xgb.wallstcn.com/v2/policies/get/3")
        ).done(new DoneCallback<MultipleResults>() {
            public void onDone(MultipleResults results) {
                System.out.println(results.get(0).getResult());
                System.out.println(results.get(1).getResult());
            }
        }).fail(new FailCallback<OneReject>() {
            public void onFail(OneReject result) {
                System.out.println(result);
            }
        });
        */


        //Pipe example
        /*
        getPromise(
               "http://api.xgb.wallstcn.com/v2/policies/get/1"
        ).done(new DoneCallback() {
            @Override
            public void onDone(Object result) {
                System.out.println(result);
            }
        }).fail(new FailCallback() {
            @Override
            public void onFail(Object result) {
                System.out.println(result);
            }
        }).then(new DonePipe() {
            @Override
            public Promise pipeDone(Object result) {
                return dm.when(
                        getPromise("http://api.xgb.wallstcn.com/v2/policies/get/2"),
                        getPromise("http://api.xgb.wallstcn.com/v2/policies/get/3")
                );
            }
        }).done(new DoneCallback<MultipleResults>() {
            public void onDone(MultipleResults results) {
                System.out.println(results.get(0).getResult());
                System.out.println(results.get(1).getResult());
            }
        }).fail(new FailCallback<OneReject>() {
            public void onFail(OneReject result) {
                System.out.println(result);
            }
        });
        */


    }

}
