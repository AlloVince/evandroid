package com.example.allovince.evandroid;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.allovince.evandroid.databinding.FragmentMainBinding;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;

import resthttp.Policy;
import resthttp.RestfulClient;
import resthttp.execption.ClientInputException;

import android.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentMainBinding binding = DataBindingUtil.setContentView(this, R.layout.fragment_main);

        //Log.w("avnpc", RestfulClient.test());
        RestfulClient.promiseApiCall(
                "http://api.wallstreetcn.com/v2/policies/get/1318"
        ).done(new DoneCallback() {
            public void onDone(Object result) {
                Response response = (Response) result;
                Gson gson = new Gson();
                try {
                    final Policy policy = gson.fromJson(response.body().string(), Policy.class);
                    //Log.w("avnpc", policy.getContent());
                    binding.setPolicy(policy);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).fail(new FailCallback() {
            public void onFail(Object obj) {
                if (!(obj instanceof ClientInputException)) {
                    return;
                }
                ClientInputException exception = (ClientInputException) obj;
                Log.w("avnpc", exception);
            }
        });
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
