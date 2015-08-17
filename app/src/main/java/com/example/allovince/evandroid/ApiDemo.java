package com.example.allovince.evandroid;

import android.util.Log;

import com.loopj.android.http.*;
import org.apache.http.Header;


/**
 * Created by allovince on 15/8/12.
 */
public class ApiDemo {
    public static void run() {

        Log.i("test", "hjjj");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.baidu.com", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                Log.i("APIDo", "started");
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                Log.i("APIDo", String.valueOf(statusCode));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.i("APIDo", e.toString());
            }

            @Override
            public void onRetry(int retryNo) {
                Log.i("APIDo", "retry");
                // called when request is retried
            }
        });
    }
}
