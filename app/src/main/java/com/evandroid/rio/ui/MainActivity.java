package com.evandroid.rio.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.evandroid.rio.R;
import com.evandroid.rio.util.ImageLoader;
import com.evandroid.rio.databinding.FragmentMainBinding;
import com.facebook.stetho.Stetho;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.evandroid.httprest.RestfulClient;
import com.evandroid.httprest.execption.ClientInputException;

import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_main);

        startActivity(new Intent(getApplicationContext(), RecyActivity.class));
        /*
        RestfulClient.promiseApiCall(
                "http://api.wallstreetcn.com/v2/posts"
        ).done(new DoneCallback<Response>() {
            public void onDone(Response response) {
                Log.w("avnpc", "onDone");
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
                Log.w("avnpc", posts.getResults().get(0).getTitle());
                Class nextClass = RecyActivity.class;
                Intent intent = new Intent(getApplicationContext(), nextClass);
                //intent.putStringArrayListExtra("images", images);
                //intent.putStringArrayListExtra("titles", titles);
                startActivity(intent);
            }
        }).fail(new FailCallback<Object>() {
            public void onFail(Object obj) {
                Log.w("avnpc", (String) obj);
                if (!(obj instanceof ClientInputException)) {
                    return;
                }
                ClientInputException exception = (ClientInputException) obj;
                //Log.w("avnpc", exception);
            }
        });
        */

        /*
        Button btn = (Button) findViewById(R.id.btn_loading);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadImage();
                loadText();
            }
        });
        */

        findViewById(R.id.btn_sgv).setOnClickListener(this);
        findViewById(R.id.btn_listview).setOnClickListener(this);
        findViewById(R.id.btn_sgv_empty_view).setOnClickListener(this);
        findViewById(R.id.btn_sgv_fragment).setOnClickListener(this);
    }

    public void onClick(final View v) {

        Intent intent = new Intent(getApplicationContext(), RecyActivity.class);
        //intent.putStringArrayListExtra("images", images);
        //intent.putStringArrayListExtra("titles", titles);
        startActivity(intent);
    }


    public void onClick2(final View v) {

        RestfulClient.promiseApiCall(
                "http://api.wallstreetcn.com/v2/posts"
                //"http://www.javhip.com/cn/released/currentPage/1"
                //"http://movie.douban.com/top250"

        ).done(new DoneCallback<Response>() {
            public void onDone(Response response) {
                ArrayList<String> images = new ArrayList<>();
                ArrayList<String> titles = new ArrayList<>();
                try {
                    String html = response.body().string();
                    Document doc = Jsoup.parse(html);

                    Elements items = doc.select("ol.grid_view .item");
                    for (Element item : items) {
                        images.add(item.select("img").attr("src").replace("/ipst/", "/lpst/"));
                        titles.add(item.select("img").attr("alt"));
                    }
//                    Elements items = doc.select(".movie-box");
//                    for (Element item : items) {
//                        images.add(item.select("img").attr("src").replace("ps.jpg", "pl.jpg"));
//                        titles.add(item.select("img").attr("title"));
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Class nextClass;
                switch (v.getId()) {
                    /*
                    case R.id.btn_sgv:
                        nextClass = StaggeredGridActivity.class;
                        break;
                    case R.id.btn_sgv_fragment:
                        nextClass = StaggeredGridActivityFragment.class;
                        break;
                    case R.id.btn_sgv_empty_view:
                        nextClass = StaggeredGridEmptyViewActivity.class;
                        break;
                    default:
                        nextClass = ListViewActivity.class;
                    */
                    default:
                        nextClass = RecyActivity.class;

                }
                Intent intent = new Intent(getApplicationContext(), nextClass);
                intent.putStringArrayListExtra("images", images);
                intent.putStringArrayListExtra("titles", titles);
                startActivity(intent);
            }
        }).fail(new FailCallback<Object>() {
            public void onFail(Object obj) {
                if (!(obj instanceof ClientInputException)) {
                    return;
                }
                ClientInputException exception = (ClientInputException) obj;
                Log.w("avnpc", exception);
            }
        });
    }

    public void loadImage() {
        Picasso img = ImageLoader.getInstance(this);
        img.setLoggingEnabled(true);
        img.setIndicatorsEnabled(true);
        img.load("http://posts.cdn.wallstcn.com/89/d3/4c/13-money-printing-fed-policy.jpg")
                .into((ImageView) findViewById(R.id.image_first));

        //Picasso.with(this).load("http://pics.dmm.co.jp/digital/video/h_295ecr00064/h_295ecr00064pl.jpg").into((ImageView) findViewById(R.id.image_first));
    }


    public void loadText() {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
