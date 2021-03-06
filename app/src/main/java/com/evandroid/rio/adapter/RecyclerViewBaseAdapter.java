package com.evandroid.rio.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by allovince on 15/8/31.
 */
public abstract class RecyclerViewBaseAdapter<VH extends RecyclerView.ViewHolder, Item> extends RecyclerView.Adapter<VH> {
    private ArrayList<Object> footer = new ArrayList<>();
    private ArrayList<Object> header = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    final static public int ITEM_TYPE_DEFAULT = 1;
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
        int listSize = header.size() + items.size();
        footer = new ArrayList<>();
        return new int[]{listSize, listSize + footerSize};
    }

    public int[] setItems(ArrayList<Item> m) {
        items = m;
        Log.d("avnpc", String.format("Set data %s", m.toString()));
        return new int[]{0, m.size()};
    }

    public int[] appendItems(ArrayList<Item> m) {
        int start = items.size();
        items.addAll(m);
        Log.d("avnpc", String.format("Appended data %s", m.toString()));
        return new int[]{start, items.size()};
    }

    public int[] prependItems(ArrayList<Item> m) {
        items.addAll(0, m);
        Log.d("avnpc", String.format("Prepend data %s", m.toString()));
        return new int[]{0, m.size()};
    }

    public int[] updateItems(ArrayList<Item> m, int position) {
        int end = position + m.size();
        int j = 0;
        for (int i = position; i < end; i++) {
            items.set(i, m.get(j));
            j++;
        }
        Log.d("avnpc", String.format("New movie list updated from position %d, data: %s", position, m.toString()));
        return new int[]{position, end};
    }

    public int[] updateItem(Item m, int position) {
        items.set(position, m);
        return new int[]{position, position};
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getItemCount() {
        int count = header.size() + items.size() + footer.size();
        Log.v("avnpc", String.format("Get item count %d {header: %d, items: %d, footer:%d}", count, header.size(), items.size(), footer.size()));
        return count;
    }

    public boolean isHeader(int position) {
        if (header.size() > 0 && position < header.size()) {
            return true;
        }
        return false;
    }

    public boolean isFooter(int position) {
        if (header.size() == 0 && (position > items.size() - 1) || header.size() > 0 && position > (header.size() + items.size() - 1)) {
            return true;
        }
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            Log.d("avnpc", String.format("Get item type as ITEM_TYPE_HEADER, header size: %d, movie size: %d, position %d", header.size(), items.size(), position));
            return ITEM_TYPE_HEADER;
        }

        if (isFooter(position)) {
            Log.d("avnpc", String.format("Get item type as ITEM_TYPE_FOOTER, header size: %d, movie size: %d, position %d", header.size(), items.size(), position));
            return ITEM_TYPE_FOOTER;
        }

        Log.d("avnpc", String.format("Get item type as ITEM_TYPE_DEFAULT, header size: %d, movie size: %d, position %d", header.size(), items.size(), position));
        return ITEM_TYPE_DEFAULT;
    }
}
