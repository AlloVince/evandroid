package com.evandroid.rio.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evandroid.rio.R;
import com.evandroid.rio.model.Movie;
import com.evandroid.rio.ui.CastListViewHolder;
import com.evandroid.rio.util.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

;

/**
 * Created by allovince on 15/9/23.
 */
public class CastListAdapter extends RecyclerViewBaseAdapter<CastListViewHolder, Movie.CastsEntity> {
    private Picasso imgLoader;

    public CastListAdapter(ArrayList<Movie.CastsEntity> m) {
        setItems(m);
    }

    public CastListAdapter() {
    }

    @Override
    public CastListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        imgLoader = ImageLoader.getInstance(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_list_recycler_view_item, parent, false);
        CastListViewHolder viewHolder = new CastListViewHolder(view, viewType);
        Log.v("avnpc", String.format("Created view holder by type(%d) %s", viewType, viewHolder.toString()));
        return viewHolder;
    }

    public void onBindViewHolder(CastListViewHolder holder, int position) {
        Movie.CastsEntity cast = getItem(position);
        holder.textView.setText(cast.getName());
        holder.cast = cast;
        String imageUrl = cast.getAvatars().getLarge();
        Log.d("avnpc", String.format("Binded %s to %s, position #%d", cast, holder.toString(), position));
        if (!("".equals(imageUrl))) {
            Log.d("avnpc", imageUrl);
            imgLoader.load(imageUrl).into(holder.imageView);
        }
    }

}
