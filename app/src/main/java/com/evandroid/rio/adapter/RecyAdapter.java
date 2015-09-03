package com.evandroid.rio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evandroid.rio.R;
import com.evandroid.rio.util.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by allovince on 15/8/31.
 */
public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
    private ArrayList<String> titles;
    private ArrayList<String> images;
    private Picasso imgLoader;

    public void setData(ArrayList<String> t, ArrayList<String> i) {
        titles = t;
        images = i;
    }

    public RecyAdapter(ArrayList<String> t, ArrayList<String> i) {
        titles = t;
        images = i;
    }


    public RecyAdapter() {
        /*
        titles = new ArrayList<>();
        titles.add("foo");
        titles.add("bar");

        images = new ArrayList<>();
        images.add("http://posts.cdn.wallstcn.com/60/c2/83/-3.jpg");
        images.add("http://posts.cdn.wallstcn.com/ae/70/79/Sea4an.jpg");
        */
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item, parent, false);
        imgLoader = ImageLoader.getInstance(parent.getContext());
        imgLoader.setLoggingEnabled(true);
        imgLoader.setIndicatorsEnabled(true);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(titles.get(position));
        imgLoader.load(images.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return titles != null ? titles.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.news_desc);
            imageView = (ImageView) itemView.findViewById(R.id.news_photo);
        }
    }
}
