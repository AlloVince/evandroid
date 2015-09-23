package com.evandroid.rio.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.evandroid.rio.model.Movie;

import java.util.ArrayList;

/**
 * Created by allovince on 15/8/31.
 */
public abstract class RecyclerViewBaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private ArrayList<Object> footer = new ArrayList<>();
    private ArrayList<Object> header = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();

    final static public int ITEM_TYPE_MOVIE = 1;
    final static public int ITEM_TYPE_FOOTER = 2;
    final static public int ITEM_TYPE_HEADER = 3;

    public void addHeader() {
        if (header.size() < 1) {
            header.add(new Object());
        }
    }

    public int getHeaderCount() {
        return header.size();
    }

    public void addFooter() {
        if (footer.size() < 1) {
            footer.add(new Object());
        }
    }

    public int getFooterCount() {
        return footer.size();
    }

    public int[] removeFooter() {
        int footerSize = footer.size();
        int listSize = header.size() + movies.size();
        footer = new ArrayList<>();
        return new int[]{listSize, listSize + footerSize};
    }

    public int[] setData(ArrayList<Movie> m) {
        movies = m;
        Log.d("avnpc", String.format("Set data %s", m.toString()));
        return new int[]{0, m.size()};
    }

    public int[] appendData(ArrayList<Movie> m) {
        int start = movies.size();
        movies.addAll(m);
        Log.d("avnpc", String.format("Appended data %s", m.toString()));
        return new int[]{start, movies.size()};
    }

    public int[] prependData(ArrayList<Movie> m) {
        movies.addAll(0, m);
        Log.d("avnpc", String.format("Prepend data %s", m.toString()));
        return new int[]{0, m.size()};
    }

    public int[] updateData(ArrayList<Movie> m, int position) {
        int end = position + m.size();
        int j = 0;
        for (int i = position; i < end; i++) {
            movies.set(i, m.get(j));
            j++;
        }
        Log.d("avnpc", String.format("New movie list updated from position %d, data: %s", position, m.toString()));
        return new int[]{position, end};
    }

    public Movie getItem(int position) {
        return movies.get(position);
    }

    public ArrayList<Movie> getItems() {
        return movies;
    }

    public int getItemCount() {
        int count = header.size() + movies.size() + footer.size();
        Log.v("avnpc", String.format("Get item count %d {header: %d, movies: %d, footer:%d}", count, header.size(), movies.size(), footer.size()));
        return count;
    }

    public boolean isHeader(int position) {
        if (header.size() > 0 && position < header.size()) {
            return true;
        }
        return false;
    }

    public boolean isFooter(int position) {
        if (header.size() == 0 && (position > movies.size() - 1) || header.size() > 0 && position > (header.size() + movies.size() - 1)) {
            return true;
        }
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            Log.d("avnpc", String.format("Get item type as ITEM_TYPE_HEADER, header size: %d, movie size: %d, position %d", header.size(), movies.size(), position));
            return ITEM_TYPE_HEADER;
        }

        if (isFooter(position)) {
            Log.d("avnpc", String.format("Get item type as ITEM_TYPE_FOOTER, header size: %d, movie size: %d, position %d", header.size(), movies.size(), position));
            return ITEM_TYPE_FOOTER;
        }

        Log.d("avnpc", String.format("Get item type as ITEM_TYPE_MOVIE, header size: %d, movie size: %d, position %d", header.size(), movies.size(), position));
        return ITEM_TYPE_MOVIE;
    }
}
