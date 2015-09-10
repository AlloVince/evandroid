package com.evandroid.rio.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.Adapter;

import com.evandroid.rio.R;
import com.evandroid.rio.model.Posts;
import com.evandroid.rio.adapter.RecyAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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


import com.evandroid.httprest.RestfulClient;
import com.evandroid.httprest.execption.ClientInputException;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecyActivityFragment extends Fragment {

    public static final String LAYOUT_STYLE_LIST = "List";
    public static final String LAYOUT_STYLE_GRID = "Grid";
    public static final String LAYOUT_STYLE_STAGGERED = "Staggered";

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    AlphaInAnimationAdapter adapter;
    ArrayList<String> images;
    ArrayList<String> titles;
    Integer pageNumber = 1;

    public RecyActivityFragment() {
        images = new ArrayList<>();
        titles = new ArrayList<>();
    }

    public void changeLayout(String type) {

        Log.w("avnpc", "Layout change...");
        switch (type) {
            case LAYOUT_STYLE_GRID:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case LAYOUT_STYLE_LIST:
                Log.w("avnpc", "Layout changed");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                break;
            default:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
        }
        //recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_recy, container, false);

        /*
        Intent intent = getActivity().getIntent();
        final ArrayList<String> images = intent.getStringArrayListExtra("images");
        final ArrayList<String> titles = intent.getStringArrayListExtra("titles");
        */

        adapter = new AlphaInAnimationAdapter(new RecyAdapter());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setItemAnimator(new LandingAnimator());
        //recyclerView.setItemAnimator(new FadeInAnimator());
        recyclerView.setItemAnimator(new SlideInLeftAnimator());

        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                pageNumber++;
                callApi();
            }
        });

        callApi();

        return view;
    }

    private void callApi() {
        RestfulClient.promiseApiCall(
                getDoubanUrl(pageNumber)
                //"http://api.wallstreetcn.com/v2/posts"
                //"http://www.javhip.com/cn/released/currentPage/1"
        ).done(new DoneCallback<Response>() {
            public void onDone(Response response) {
                updateDoubanAdapter(response);
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).fail(new FailCallback<Object>() {
            public void onFail(Object obj) {
                pageNumber--;
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
        return String.format("http://movie.douban.com/top250?start=%d", page * 25);
    }

    private void updateDmmAdapter(Response response) {
        String html = null;
        try {
            html = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(html);
        Elements items = doc.select(".movie-box");
        for (Element item : items) {
            //images.add(item.select("img").attr("src").replace("ps.jpg", "pl.jpg"));
            images.add(item.select("img").attr("src"));
            titles.add(item.select("img").attr("title"));
        }

        Adapter wrappedAdapter = adapter.getWrappedAdapter();
        //((RecyAdapter) wrappedAdapter).setData(titles, images);
    }

    private void updateDoubanAdapter(Response response) {
        try {
            String html = response.body().string();
            Document doc = Jsoup.parse(html);
            Elements items = doc.select("ol.grid_view .item");
            for (Element item : items) {
                images.add(item.select("img").attr("src").replace("/ipst/", "/lpst/"));
                titles.add(item.select("img").attr("alt"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Adapter wrappedAdapter = adapter.getWrappedAdapter();
        //((RecyAdapter) wrappedAdapter).setData(titles, images);
//        adapter.setData(titles, images);
    }

    private void updateWscnAdapter(Response response) {
        Gson gson = new Gson();
        String body = null;
        Posts posts = null;
        try {
            body = response.body().string();
            posts = gson.fromJson(body, Posts.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        List<Posts.ResultsEntity> results = posts.getResults();
        if (results.size() > 0) {
            Log.d("avnpc", "get results");
            for (Posts.ResultsEntity post : results) {
                String title = post.getTitle();
                String url = post.getImageUrl() + "!index-news-cover";
                Log.d("avnpc", "title: " + title);
                Log.d("avnpc", "url: " + url);
                titles.add(title);
                images.add(url);
            }
        }
        Adapter wrappedAdapter = adapter.getWrappedAdapter();
        //((RecyAdapter) wrappedAdapter).setData(titles, images);
//        adapter.setData(titles, images);
    }
}
