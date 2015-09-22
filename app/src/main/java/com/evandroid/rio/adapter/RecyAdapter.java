package com.evandroid.rio.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evandroid.rio.R;
import com.evandroid.rio.model.Movie;
import com.evandroid.rio.ui.MovieDetailActivity;
import com.evandroid.rio.util.ImageLoader;
import com.joanzapata.iconify.widget.IconTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by allovince on 15/8/31.
 */
public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
    private ArrayList<Object> footer = new ArrayList<>();
    private ArrayList<Object> header = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private Picasso imgLoader;

    final static public int ITEM_TYPE_MOVIE = 1;
    final static public int ITEM_TYPE_FOOTER = 2;
    final static public int ITEM_TYPE_HEADER = 3;

    public void addHeader() {
        if (header.size() < 1) {
            header.add(new Object());
        }
    }

    public void addFooter() {
        if (footer.size() < 1) {
            footer.add(new Object());
        }
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

    public Movie getData(int position) {
        return movies.get(position);
    }

    public RecyAdapter(ArrayList<Movie> m) {
        movies = m;
    }

    public RecyAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        imgLoader = ImageLoader.getInstance(parent.getContext());
        if (viewType == ITEM_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_recycler_view_footer, parent, false);
            ViewHolder viewHolder = new ViewHolder(view, viewType);
            Log.v("avnpc", String.format("Created view holder as header by type(%d) %s", viewType, viewHolder.toString()));
            return viewHolder;
        }

        if (viewType == ITEM_TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_recycler_view_footer, parent, false);
            ViewHolder viewHolder = new ViewHolder(view, viewType);
            Log.v("avnpc", String.format("Created view holder as footer by type(%d) %s", viewType, viewHolder.toString()));
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_recycler_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, viewType);
        Log.v("avnpc", String.format("Created view holder by type(%d) %s", viewType, viewHolder.toString()));
        return viewHolder;
    }

    public void onBindHeaderViewHolder(ViewHolder holder, int position) {
        return;
    }

    public void onBindFooterViewHolder(ViewHolder holder, int position) {
        //TODO: Support all kinds of layout manager
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.setFullSpan(true);
        return;
    }

    /*
    public static final class PaletteTransformation implements Transformation {
        private static final PaletteTransformation INSTANCE = new PaletteTransformation();
        private static final Map<Bitmap, Palette> CACHE = new WeakHashMap<>();

        public static PaletteTransformation instance() {
            return INSTANCE;
        }

        public static Palette getPalette(Bitmap bitmap) {
            return CACHE.get(bitmap);
        }

        private PaletteTransformation() {}

        @Override public Bitmap transform(Bitmap source) {
            Palette palette = Palette.generate(source);
            CACHE.put(source, palette);
            return source;
        }

        @Override
        public String key() {
            return "";
        }
    }
    */


    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (isHeader(position)) {
            onBindHeaderViewHolder(holder, position);
            return;
        }

        if (isFooter(position)) {
            onBindFooterViewHolder(holder, position);
            return;
        }

        int moviePosition = header.size() > 0 ? position - header.size() : position;
        Movie movie = movies.get(moviePosition);
        Log.d("avnpc", String.format("Binded %s to %s, position #%d", movie.getTitle(), holder.toString(), position));
        holder.imageView.setBackgroundColor(movie.getFill_color());
        holder.textView.setText(movie.getTitle());
        holder.movie = movie;
        String imageUrl = movie.getImages().getMedium();
        if (!("".equals(imageUrl))) {
            Log.d("avnpc", imageUrl);
            imgLoader.load(imageUrl).into(holder.imageView);
            /*
            imgLoader.load(imageUrl)
                    //.transform(PaletteTransformation.instance())
                    .into(holder.imageView, new Callback() {
                public void onSuccess() {
                    Bitmap bitmap = ((BitmapDrawable) holder.imageView.getDrawable()).getBitmap(); // Ew!
                    //Palette palette = PaletteTransformation.getPalette(bitmap);
                    Palette palette = Palette.generate(bitmap);
                    holder.imageButton.setTextColor(palette.getLightVibrantColor(0xFFFFFF));
                    holder.imageButton.setShadowLayer(2, 4, 4, palette.getDarkMutedColor(0x000000));
                }

                public void onError() {
                }
            });
            */
        }
    }

    @Override
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


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;
        public IconTextView imageButton;
        public Movie movie;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == RecyAdapter.ITEM_TYPE_FOOTER || viewType == RecyAdapter.ITEM_TYPE_HEADER) {
                return;
            }
            textView = (TextView) itemView.findViewById(R.id.news_desc);
            imageView = (ImageView) itemView.findViewById(R.id.news_photo);

            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext().getApplicationContext(), MovieDetailActivity.class);
                    intent.putExtra("DataPosition", position);
                    Log.d("avnpc", String.format("Passed position %d to detail activity", position));
                    v.getContext().startActivity(intent);
                }
            });
            /*
            imageButton = (IconTextView) itemView.findViewById(R.id.news_favor);
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Toast.makeText(
                            v.getContext(),
                            String.format("Adapter position %d, layout position %d, movie %s", getAdapterPosition(), getLayoutPosition(), movie),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });
            */
        }
    }
}
