package com.evandroid.rio.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.evandroid.rio.R;
import com.evandroid.rio.model.Movie;
import com.evandroid.rio.ui.MainActivity;
import com.evandroid.rio.util.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by allovince on 15/8/31.
 */
public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
    private ArrayList<Movie> movies = new ArrayList<>();
    private Picasso imgLoader;

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    private MainActivity activity;

    public void setData(ArrayList<Movie> m) {
        movies = m;
        activity.runOnUiThread(new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        });
        Log.d("avnpc", String.format("Set data %s", m.toString()));
    }

    public void appendData(ArrayList<Movie> m) {
        movies.addAll(m);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        });
        Log.d("avnpc", String.format("Appended data %s", m.toString()));
    }

    public void prependData(ArrayList<Movie> m) {
        movies.addAll(0, m);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        });
        Log.d("avnpc", String.format("Prepend data %s", m.toString()));
    }

    public void updateData(ArrayList<Movie> m, int position) {
        int end = position + m.size();
        int j = 0;
        for(int i = position; i < end; i++) {
            movies.set(i, m.get(j));
            j++;
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        });
        Log.d("avnpc", String.format("New movie list updated from position %d, data: %s", position, m.toString()));
    }

    public RecyAdapter(ArrayList<Movie> m) {
        movies = m;
    }

    public RecyAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item, parent, false);
        imgLoader = ImageLoader.getInstance(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(view);
        Log.v("avnpc", String.format("Created view holder %s", viewHolder.toString()));
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Log.d("avnpc", String.format("Binded %s to %s, position #%d", movie.getTitle(), holder.toString(), position));
        holder.imageView.setBackgroundColor(movie.getFill_color());
        holder.textView.setText(movie.getTitle());
        String imageUrl = movie.getImages().getMedium();
        if (!("".equals(imageUrl))) {
            Log.d("avnpc", imageUrl);
            imgLoader.load(imageUrl).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        Log.v("avnpc", String.format("Get item count %d", movies.size()));
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;
        public ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.news_desc);
            imageView = (ImageView) itemView.findViewById(R.id.news_photo);
            imageButton = (ImageButton) itemView.findViewById(R.id.news_favor);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), String.format("Adapter position %d, layout position %d", getAdapterPosition(), getLayoutPosition()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
