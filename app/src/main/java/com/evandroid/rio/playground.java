package com.evandroid.rio;

import android.webkit.URLUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedHashTreeMap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by allovince on 15/9/11.
 */
public class Playground {
    public static void main(String arg[]) {

        String json = "{\n" +
                "    data: {\n" +
                "        snapshot: {\n" +
                "            fields: [\n" +
                "                \"open_px\",\n" +
                "                \"high_px\",\n" +
                "                \"low_px\"\n" +
                "            ],\n" +
                "            600570.SS: [\n" +
                "                47.08,\n" +
                "                48.38,\n" +
                "                45.88\n" +
                "            ],\n" +
                "            000001.SZ: [\n" +
                "                11.02,\n" +
                "                11.12,\n" +
                "                10.88\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";

        GsonBuilder builder = new GsonBuilder();
        Map o = (Map) builder.create().fromJson(json, Object.class);
        o.get("data");
        System.out.println(o);
    }
}
