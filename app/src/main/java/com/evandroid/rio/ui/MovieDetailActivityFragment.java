package com.evandroid.rio.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evandroid.httprest.RestfulClient;
import com.evandroid.httprest.execption.ClientInputException;
import com.evandroid.rio.R;
import com.evandroid.rio.adapter.CastListAdapter;
import com.evandroid.rio.databinding.FragmentMovieDetailBinding;
import com.evandroid.rio.model.Movie;
import com.evandroid.rio.util.ImageLoader;
import com.google.gson.Gson;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    RecyclerView castRecyclerView;
    CastListAdapter castListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentMovieDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        final View view = binding.getRoot();
        final Movie movie = new Movie();
        binding.setMovie(movie);

        initCastRecyclerView(view);

        final Picasso imgLoader = ImageLoader.getInstance(getContext());
        final FloatingActionButton btnFavor = (FloatingActionButton) view.findViewById(R.id.detail_btn_favor);
        btnFavor.setImageDrawable(new IconDrawable(view.getContext(), FontAwesomeIcons.fa_heart_o).colorRes(R.color.white));
        btnFavor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.w("detail", "abc");
                btnFavor.setImageDrawable(new IconDrawable(view.getContext(), FontAwesomeIcons.fa_heart).colorRes(R.color.white));
            }
        });


        Intent intent = getActivity().getIntent();
        String movieId = intent.getStringExtra("MovieId");
        movieId = movieId == null ? "25774051" : movieId;
        RestfulClient.promiseApiCall(
                getResources().openRawResource(R.raw.movie_detail)
                //"https://api.douban.com/v2/movie/subject/" + movieId
        ).done(new DoneCallback<Response>() {
            public void onDone(Response response) {
                Gson gson = new Gson();
                try {
                    final Movie movie = gson.fromJson(response.body().string(), Movie.class);
                    final ArrayList<Movie.CastsEntity> castList = new ArrayList<>();
                    for (Movie.CastsEntity cast : movie.getCasts()) {
                        castList.add(cast);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            ImageView imageView = (ImageView) view.findViewById(R.id.detail_cover);
                            imgLoader.load("http://img4.douban.com/view/photo/photo/public/p2240182456.jpg").into(imageView, new Callback() {
                                @Override
                                public void onSuccess() {
                                    binding.setMovie(movie);
                                    castListAdapter.setItems(castList);
                                    castListAdapter.notifyDataSetChanged();

                                }

                                @Override
                                public void onError() {
                                    binding.setMovie(movie);
                                    castListAdapter.setItems(castList);
                                    castListAdapter.notifyDataSetChanged();

                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).fail(new FailCallback<Object>() {
            public void onFail(Object obj) {
                Log.w("avnpc", (String) obj);
                if (!(obj instanceof ClientInputException)) {
                    return;
                }
                ClientInputException exception = (ClientInputException) obj;
                Log.w("avnpc", exception.toString());
            }
        });
        return view;
    }

    private void initCastRecyclerView(View view) {
        castRecyclerView = (RecyclerView) view.findViewById(R.id.cast_recycler_view);
        //castRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        castRecyclerView.setLayoutManager(linearLayoutManager);
        castListAdapter = new CastListAdapter();
        castRecyclerView.setAdapter(castListAdapter);
        /*
        final ArrayList<Movie.CastsEntity> casts = new ArrayList<>();
        Movie.CastsEntity cast = new Movie.CastsEntity();
        cast.setName("abc");
        Movie.CastsEntity.AvatarsEntity avatar = new Movie.CastsEntity.AvatarsEntity();
        avatar.setLarge("https://img2.doubanio.com/img/celebrity/large/16385.jpg");
        cast.setAvatars(avatar);
        casts.add(cast);
        castListAdapter = new CastListAdapter(casts);
        */

        /*
        castRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        //Allow ScrollView to intercept touch events once again.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                // Handle RecyclerView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        */

        /*
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                castRecyclerView.setAdapter(castListAdapter);
                //castListAdapter.notifyDataSetChanged();
            }
        });
        */

    }
}
