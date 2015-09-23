package com.evandroid.rio.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.evandroid.httprest.RestfulClient;
import com.evandroid.httprest.execption.ClientInputException;
import com.evandroid.rio.R;
import com.evandroid.rio.databinding.FragmentMovieDetailBinding;
import com.evandroid.rio.model.Movie;
import com.evandroid.rio.util.ImageLoader;
import com.google.gson.Gson;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    public MovieDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentMovieDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        final View view = binding.getRoot();
        final Movie movie = new Movie();
        binding.setMovie(movie);

        Picasso imgLoader = ImageLoader.getInstance(getContext());
        ImageView imageView = (ImageView) view.findViewById(R.id.detail_cover);
        imgLoader.load("http://img4.douban.com/view/photo/photo/public/p2240182456.jpg").into(imageView);
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
        movieId = "".equals(movieId) ? "25774051" : movieId;
        RestfulClient.promiseApiCall(
                "https://api.douban.com/v2/movie/subject/" + movieId
        ).done(new DoneCallback<Response>() {
            public void onDone(Response response) {
                Gson gson = new Gson();
                try {
                    final Movie movie = gson.fromJson(response.body().string(), Movie.class);
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            binding.setMovie(movie);
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
}
