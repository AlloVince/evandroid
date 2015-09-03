package com.evandroid.rio.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A placeholder fragment containing a simple view.
 */
public class RecyActivityFragment extends Fragment {

    public static final String LAYOUT_STYLE_LIST = "List";
    public static final String LAYOUT_STYLE_GRID = "Grid";
    public static final String LAYOUT_STYLE_STAGGERED = "Staggered";

    RecyclerView recyclerView;
    RecyAdapter adapter;
    ArrayList<String> images;
    ArrayList<String> titles;

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

        adapter = new RecyAdapter();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        RestfulClient.promiseApiCall(
                //"http://api.wallstreetcn.com/v2/posts"
                "http://movie.douban.com/top250"
                //"http://www.javhip.com/cn/released/currentPage/1"
        ).done(new DoneCallback<Response>() {
            public void onDone(Response response) {
                //getWscnAdapter(response);
                getDoubanAdapter(response);
//                getDmmAdapter(response);
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
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

    private void getDmmAdapter(Response response) {
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
        adapter.setData(titles, images);
    }

    private void getDoubanAdapter(Response response) {
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
        adapter.setData(titles, images);
    }

    private void getWscnAdapter(Response response) {
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
        adapter.setData(titles, images);
    }
}
