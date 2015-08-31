package com.example.allovince.evandroid;

import android.content.Context;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import resthttp.RestfulClient;

/**
 * Created by allovince on 15/8/27.
 */
public class ImageLoader {

    private static Picasso picasso;

    public static Picasso getInstance(Context context) {
        if (null == picasso) {
            picasso = new Picasso.Builder(context).downloader(new OkHttpDownloader(RestfulClient.getInstance())).build();
            Picasso.setSingletonInstance(picasso);
            picasso.setLoggingEnabled(true);
            picasso.setIndicatorsEnabled(true);
        }
        return picasso;
    }

}
