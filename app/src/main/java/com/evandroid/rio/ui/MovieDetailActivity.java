package com.evandroid.rio.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.evandroid.httprest.RestfulClient;
import com.evandroid.httprest.execption.ClientInputException;
import com.evandroid.rio.databinding.FragmentMovieDetailBinding;
import com.evandroid.rio.R;
import com.evandroid.rio.model.Movie;
import com.google.gson.Gson;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.squareup.okhttp.Response;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;

import java.io.IOException;

public class MovieDetailActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        Intent intent = getIntent();
        int movieId = intent.getIntExtra("DataPosition", 25774051);

        FloatingActionButton btnFavor = (FloatingActionButton) findViewById(R.id.detail_btn_favor);
        btnFavor.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_heart_o).colorRes(R.color.white));
        /*
        Toast.makeText(
                this,
                String.format("Show movie by position %d", position),
                Toast.LENGTH_SHORT
        ).show();
        */
        final FragmentMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.fragment_movie_detail);
        Movie movie = new Movie();
        movie.setTitle("abc");
        binding.setMovie(movie);
        RestfulClient.promiseApiCall(
                "https://api.douban.com/v2/movie/subject/" + String.valueOf(movieId)
        ).done(new DoneCallback<Response>() {
            public void onDone(Response response) {
                Gson gson = new Gson();
                try {
                    final Movie movie = gson.fromJson(response.body().string(), Movie.class);
                    runOnUiThread(new Runnable() {
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
    }
}
