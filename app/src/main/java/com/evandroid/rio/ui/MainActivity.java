package com.evandroid.rio.ui;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.evandroid.httprest.RestfulClient;
import com.evandroid.httprest.execption.ClientInputException;
import com.evandroid.rio.R;
import com.evandroid.rio.adapter.RecyAdapter;
import com.evandroid.rio.model.Movie;
import com.squareup.okhttp.Response;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;

/**
 * TODO
 */
public class MainActivity extends AppCompatActivity {

    private static int UPDATE_DIRECTION_PULL_DOWN = -1;
    private static int UPDATE_DIRECTION_PULL_UP = 1;
    private static int UPDATE_DIRECTION_NO_PULL = 0;

    private boolean isLoadingMore = false;
    private boolean isRefreshing = false;

    private DrawerLayout mDrawerLayout;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    //AlphaInAnimationAdapter adapter;
    RecyAdapter adapter;
    Integer pageNumber = 1;
    Integer direction = UPDATE_DIRECTION_NO_PULL;
    public static int[] colors;
    private static int VIEW_COLUMNS = 2;
    private static int PER_PAGE = 25;
    private static int MAX_ITEM = 250;

    @Override
    protected void onStart() {
        super.onStart();
        //Reset page number
        pageNumber = 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors = new int[]{
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.orange),
                ContextCompat.getColor(this, R.color.yellow),
                ContextCompat.getColor(this, R.color.green),
        };
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        initRecyclerView();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                callApi(UPDATE_DIRECTION_PULL_DOWN);
            }
        });

        callApi(UPDATE_DIRECTION_NO_PULL);
    }

    private void initRecyclerView() {
        adapter = new RecyAdapter();
        adapter.addFooter();
        adapter.addHeader();
        //RecyAdapter wrapAdapter = new RecyAdapter();
        //wrapAdapter.setActivity(this);
        //adapter = new AlphaInAnimationAdapter(wrapAdapter);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(VIEW_COLUMNS, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new ScaleInAnimator());
        recyclerView.setAdapter(adapter);

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            recyclerView.setOnScrollChangeListener(new RecyclerView.OnScrollChangeListener() {
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int[] pastVisiblesItems = layoutManager.findFirstVisibleItemPositions(null);
                    Log.v("avnpc", String.format("Scrolling: Visible item count %d; total item count %d; past visible items %s", visibleItemCount, totalItemCount, pastVisiblesItems[VIEW_COLUMNS - 1]));
                    if ((visibleItemCount + pastVisiblesItems[VIEW_COLUMNS - 1]) >= totalItemCount) {
                        Log.d("avnpc", String.format("Reached bottom. Visible item count %d; total item count %d; past visible items %s", visibleItemCount, totalItemCount, pastVisiblesItems[VIEW_COLUMNS - 1]));
                        callApi(UPDATE_DIRECTION_PULL_UP);
                    }
                }
            });
            return;
        }
        */

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int[] pastVisiblesItems = layoutManager.findFirstVisibleItemPositions(null);
                Log.v("avnpc", String.format("Scrolling: Visible item count %d; total item count %d; past visible items %s", visibleItemCount, totalItemCount, pastVisiblesItems[VIEW_COLUMNS - 1]));
                if ((visibleItemCount + pastVisiblesItems[VIEW_COLUMNS - 1]) >= totalItemCount) {
                    Log.d("avnpc", String.format("Reached bottom. Visible item count %d; total item count %d; past visible items %s", visibleItemCount, totalItemCount, pastVisiblesItems[VIEW_COLUMNS - 1]));
                    callApi(UPDATE_DIRECTION_PULL_UP);
                    showLoadingMore();
                }
            }
        });
    }

    private void showLoadingMore() {

    }

    private void hideLoadingMore() {

    }

    private void prepareCards(int direction) {
        if (true == isReachedLastPage()) {
            return;
        }
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < PER_PAGE; i++) {
            movies.add(new Movie());
        }

        Log.d("avnpc", String.format("Inited movie list, %d dummy movies created, direction: %d", movies.size(), direction));

        //RecyclerView.Adapter wrappedAdapter = adapter.getWrappedAdapter();
        if (direction > 0) {
            adapter.appendData(movies);
            return;
        } else if (direction < 0) {
            adapter.prependData(movies);
            return;
        }
        adapter.setData(movies);
    }

    private boolean isReachedLastPage() {
        //TODO: not correct for last page
        if (pageNumber * PER_PAGE > MAX_ITEM) {
            return true;
        }
        return false;
    }

    private void callApi(int direction) {
        if (true == isReachedLastPage()) {
            return;
        }
        if (true == isLoadingMore) {
            Log.d("avnpc", String.format("Scroll event triggered by skipped by is loading"));
            return;
        }
        isLoadingMore = true;
        this.direction = direction;
        prepareCards(direction);
        Log.i("avnpc", String.format("Calling API %s, direction %d, page %d", getDoubanUrl(pageNumber), direction, pageNumber));
        RestfulClient.promiseApiCall(
                getDoubanUrl(pageNumber)
                //getDmmUrl(pageNumber)
        ).done(new DoneCallback<Response>() {
            public void onDone(final Response response) {
                isLoadingMore = false;
                pageNumber++;
                final int[] range = updateDoubanAdapter(response);
                runOnUiThread(new Runnable() {
                    public void run() {
                        notifyRange(range);
                        //adapter.refreshVisibleViewHolders(recyclerView.getLayoutManager());
                        Log.d("avnpc", String.format("Called notify on UI Thread"));
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).fail(new FailCallback<Object>() {
            public void onFail(Object obj) {
                isLoadingMore = false;
                swipeRefreshLayout.setRefreshing(false);
                Log.w("avnpc", (String) obj);
                if (!(obj instanceof ClientInputException)) {
                    return;
                }
                ClientInputException exception = (ClientInputException) obj;
                Log.w("avnpc", exception.toString());
            }
        });
    }

    private String getDoubanUrl(int page) {
        page = page > 0 ? page - 1 : 0;
        return String.format("http://movie.douban.com/top250?start=%d", page * PER_PAGE);
    }

    private void notifyRange(final int[] range) {
        if (range.length < 2) {
            return;
        }
        runOnUiThread(new Runnable() {
            public void run() {
                for(int i = range[0]; i < range[1]; i++) {
                    adapter.notifyItemChanged(i);
                }
            }
        });

    }

    private int[] updateDoubanAdapter(Response response) {
        Log.i("avnpc", String.format("Call API %s success", response.request().httpUrl().toString()));
        final ArrayList<Movie> movieList = new ArrayList<>();
        int[] changedRange = new int[]{0, 0};
        try {
            String html = response.body().string();
            Document doc = Jsoup.parse(html);
            Elements items = doc.select("ol.grid_view .item");
            Pattern r = Pattern.compile("(\\d+)");
            for (Element item : items) {
                Movie movie = new Movie();
                String link = item.select(".pic a").attr("href");
                Matcher matcher = r.matcher(link);
                if (matcher.find()) {
                    movie.setId(matcher.group(0));
                }
                movie.setTitle(item.select("img").attr("alt"));
                movie.getImages().setMedium(item.select("img").attr("src").replace("/ipst/", "/lpst/"));
                movieList.add(movie);
            }
            Log.d("avnpc", String.format("Movie list data regenerated, current direction %d, movies %s", direction, movieList.toString()));
            if (direction == UPDATE_DIRECTION_PULL_UP) {
                Log.d("avnpc", String.format("Movie list (size %d) updating to adapter position %d", movieList.size(), (pageNumber - 2) * PER_PAGE));
                changedRange = adapter.updateData(movieList, (pageNumber - 2) * PER_PAGE);
            } else {
                Log.d("avnpc", String.format("Movie list (size %d) updating to adapter position %d", movieList.size(), 0));
                changedRange = adapter.updateData(movieList, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return changedRange;
    }

    private String getDmmUrl(int page) {
        return String.format("http://www.javtag.com/cn/released/currentPage/%d", page);
    }

    private RecyAdapter updateDmmAdapter(Response response) {
        Log.d("avnpc", String.format("Call API %s success", response.request().httpUrl().toString()));
        ArrayList<Movie> movieList = new ArrayList<>();
        //RecyAdapter wrappedAdapter = (RecyAdapter) (RecyclerView.Adapter) adapter.getWrappedAdapter();
        try {
            String html = response.body().string();
            Document doc = Jsoup.parse(html);
            Elements items = doc.select(".movie-box");
            Pattern r = Pattern.compile("(\\d+)");
            for (Element item : items) {
                Movie movie = new Movie();
                //images.add(item.select("img").attr("src").replace("ps.jpg", "pl.jpg"));
                movie.setTitle(item.select("img").attr("title"));
                movie.getImages().setMedium(item.select("img").attr("src"));
                movieList.add(movie);
            }
            Log.d("avnpc", String.format("Movie list data regenerated, current direction %d, movies %s", direction, movieList.toString()));
            if (direction == UPDATE_DIRECTION_PULL_UP) {
                Log.d("avnpc", String.format("Movie list (size %d) updating to adapter position %d", movieList.size(), (pageNumber - 2) * PER_PAGE));
                adapter.updateData(movieList, (pageNumber - 2) * PER_PAGE);
                return adapter;
            }
            Log.d("avnpc", String.format("Movie list (size %d) updating to adapter position %d", movieList.size(), 0));
            adapter.updateData(movieList, 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
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

