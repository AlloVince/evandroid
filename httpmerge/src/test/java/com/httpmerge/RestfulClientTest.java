package com.httpmerge;

import com.httpmerge.execption.ClientInputException;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import junit.framework.TestCase;

import org.jdeferred.DoneCallback;
import org.jdeferred.DonePipe;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * Created by allovince on 15/8/18.
 */
public class RestfulClientTest extends TestCase {

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("hello, world!"));
        server.enqueue(new MockResponse().setBody("sup, bra?"));
        server.enqueue(new MockResponse().setBody("yo dog"));
        server.start();
    }

    @Test
    public void testRegisterServerFailedHandler() throws Exception {

    }

    @Test
    public void testGetServerFaileHandler() throws Exception {

    }

    @Test(timeout=10000)
    public void testPromiseApiCall() throws Exception {
        final URL url = server.getUrl("/v2/chat");

       // System.out.println(0);


        RestfulClient.promiseApiCall(url.toString()).done(new DoneCallback() {

            public void onDone(Object result) {
                Response response = (Response) result;
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).fail(new FailCallback() {
            public void onFail(Object obj) {
                if (!(obj instanceof ClientInputException)) {
                    return;
                }
                ClientInputException exception = (ClientInputException) obj;
                System.out.println(exception);
            }
        }).then(new DonePipe() {
            public Promise pipeDone(Object result) {
                return RestfulClient.promiseApiCall(url.toString());
            }
        }).done(new DoneCallback() {
            public void onDone(Object result) {
                Response response = (Response) result;
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(3000);
    }

    @Test
    public void testPromiseApiCall1() throws Exception {

    }

    @Test
    public void testPromiseApiCall2() throws Exception {

    }

    public static void main(String[] args) {

        /*
        RestfulClientTest t = new RestfulClientTest();
        try {
            t.testPromiseApiCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */


        RestfulClient.promiseApiCall(
                //"http://api.wallstreetcn.com/v2/users/me"
                //"http://pay.wallstreetcn.com/"
                "http://www.baidu.com/"
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
                System.out.println(exception);
            }
        });
    }
}