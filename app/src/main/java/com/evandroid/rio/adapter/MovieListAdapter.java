package com.evandroid.rio.adapter;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evandroid.rio.R;
import com.evandroid.rio.model.Movie;
import com.evandroid.rio.util.ImageLoader;
import com.evandroid.rio.ui.MovieListViewHolder;
;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by allovince on 15/9/23.
 */
public class MovieListAdapter extends RecyclerViewBaseAdapter<MovieListViewHolder, Movie> {
    private Picasso imgLoader;

    public MovieListAdapter(ArrayList<Movie> m) {
        setItems(m);
    }

    public MovieListAdapter() {
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        imgLoader = ImageLoader.getInstance(parent.getContext());
        if (viewType == ITEM_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_recycler_view_footer, parent, false);
            MovieListViewHolder viewHolder = new MovieListViewHolder(view, viewType);
            Log.v("avnpc", String.format("Created view holder as header by type(%d) %s", viewType, viewHolder.toString()));
            return viewHolder;
        }

        if (viewType == ITEM_TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_recycler_view_footer, parent, false);
            MovieListViewHolder viewHolder = new MovieListViewHolder(view, viewType);
            Log.v("avnpc", String.format("Created view holder as footer by type(%d) %s", viewType, viewHolder.toString()));
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_recycler_view_item, parent, false);
        MovieListViewHolder viewHolder = new MovieListViewHolder(view, viewType);
        Log.v("avnpc", String.format("Created view holder by type(%d) %s", viewType, viewHolder.toString()));
        return viewHolder;
    }

    public void onBindHeaderViewHolder(MovieListViewHolder holder, int position) {
        return;
    }

    public void onBindFooterViewHolder(MovieListViewHolder holder, int position) {
        //TODO: Support all kinds of layout manager
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.setFullSpan(true);
        return;
    }

    public void onBindViewHolder(MovieListViewHolder holder, int position) {
        if (isHeader(position)) {
            onBindHeaderViewHolder(holder, position);
            return;
        }

        if (isFooter(position)) {
            onBindFooterViewHolder(holder, position);
            return;
        }

        int moviePosition = getHeaderCount() > 0 ? position - getHeaderCount() : position;
        Movie movie = getItem(moviePosition);
        Log.d("avnpc", String.format("Binded %s to %s, position #%d", movie.getTitle(), holder.toString(), position));
        holder.imageView.setBackgroundColor(movie.getFill_color());
        holder.textView.setText(movie.getTitle());
        holder.movie = movie;
        String imageUrl = movie.getImages().getMedium();
        if (!("".equals(imageUrl))) {
            Log.d("avnpc", imageUrl);
            imgLoader.load(imageUrl).into(holder.imageView);
        }
    }

}
