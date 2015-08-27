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
                "http://www.javhip.com/cn"
        ).done(new DoneCallback() {
            public void onDone(Object response) {
                ArrayList<String> imgSrc = new ArrayList<String>();
                ArrayList<String> title = new ArrayList<String>();
                try {
                    String html = ((Response) response).body().string();
                    //System.out.println(html);
                    Document doc = Jsoup.parse(html);
                    Elements items = doc.select(".movie-box");
                    //System.out.println(items);
                    for (Element item : items) {
                        //System.out.println(item.select("img").attr("src"));
                        imgSrc.add(item.select("img").attr("src"));
                        //imgSrc.add(item.select("img").attr("src"));
                        title.add(item.select("img").attr("title"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).fail(new FailCallback() {
            public void onFail(Object result) {
                System.out.println("Failed");
                ((Exception) result).printStackTrace();
            }
        });

    }
}
