package com.evandroid.rio.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.evandroid.rio.R;
import com.evandroid.rio.adapter.RecyclerViewBaseAdapter;
import com.evandroid.rio.viewmodel.Movie;
import com.joanzapata.iconify.widget.IconTextView;

/**
 * Created by allovince on 15/9/23.
 */
public class MovieListViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public ImageView imageView;
    public IconTextView imageButton;
    public Movie movie;

    public MovieListViewHolder(View itemView, int viewType) {
        super(itemView);
        if (viewType == RecyclerViewBaseAdapter.ITEM_TYPE_FOOTER || viewType == RecyclerViewBaseAdapter.ITEM_TYPE_HEADER) {
            return;
        }
        textView = (TextView) itemView.findViewById(R.id.news_desc);
        imageView = (ImageView) itemView.findViewById(R.id.news_photo);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = getAdapterPosition();
                Intent intent = new Intent(v.getContext().getApplicationContext(), MovieDetailActivity.class);
                intent.putExtra("ViewPosition", position);
                intent.putExtra("MovieId", movie.getId());
                Log.d("avnpc", String.format("Passed position %d to detail activity", position));
                v.getContext().startActivity(intent);
            }
        });
    }
}
