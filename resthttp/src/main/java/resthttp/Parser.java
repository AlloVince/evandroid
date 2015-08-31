package resthttp;

import com.squareup.okhttp.Response;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by allovince on 15/8/26.
 */
public class Parser {
    public static void main(String[] args) {
        RestfulClient.promiseApiCall(
                //"http://www.javhip.com/cn/released/currentPage/1"
                "http://movie.douban.com/top250"
        ).done(new DoneCallback<Response>() {
            public void onDone(Response response) {
                ArrayList<String> images = new ArrayList<>();
                ArrayList<String> titles = new ArrayList<>();
                try {
                    String html = response.body().string();
                    Document doc = Jsoup.parse(html);

                    Elements items = doc.select("ol.grid_view .item");
                    for (Element item : items) {
                        images.add(item.select("img").attr("src"));
                        titles.add(item.select("img").attr("alt"));
                    }
                    /*
                    Elements items = doc.select(".movie-box");
                    for (Element item : items) {
                        images.add(item.select("img").attr("src"));
                        titles.add(item.select("img").attr("title"));
                    }
                    */
                    System.out.println(images);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).fail(new FailCallback<Object>() {
            public void onFail(Object result) {
                System.out.println("Failed");
                ((Exception) result).printStackTrace();
            }
        });

    }
}
