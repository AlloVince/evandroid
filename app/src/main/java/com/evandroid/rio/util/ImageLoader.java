package com.evandroid.rio.util;

import android.content.Context;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by allovince on 15/8/27.
 */
public class ImageLoader {

    private static Picasso picasso;

    public static Picasso getInstance(Context context) {
        if (null == picasso) {
            OkHttpDownloader downloader = new OkHttpDownloader(context, Integer.MAX_VALUE);
            //NOTE: Picasso require context to enable disk cache
            picasso = new Picasso.Builder(context).downloader(downloader).build();
            picasso.setLoggingEnabled(true);
            picasso.setIndicatorsEnabled(true);
            Picasso.setSingletonInstance(picasso);
        }
        return picasso;
    }

}
