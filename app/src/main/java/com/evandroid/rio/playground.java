package com.evandroid.rio;

import android.webkit.URLUtil;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by allovince on 15/9/11.
 */
public class playground {
    public static void main(String arg[]) {

        String path = null;
        try {
            path = new URL("http://wallstreetcn.com/node/subscription/1").getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(path);
    }
}
