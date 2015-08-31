package com.example.allovince.evandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import resthttp.RestfulClient;

public class ListViewActivity extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        setTitle("ListView");

        Intent intent = getIntent();
        ArrayList<String> images = intent.getStringArrayListExtra("images");
        ArrayList<String> titles = intent.getStringArrayListExtra("titles");

        final ListView listView = (ListView) findViewById(R.id.list_view);

        LayoutInflater layoutInflater = getLayoutInflater();

        View header = layoutInflater.inflate(R.layout.list_item_header_footer, null);
        View footer = layoutInflater.inflate(R.layout.list_item_header_footer, null);
        TextView txtHeaderTitle = (TextView) header.findViewById(R.id.txt_title);
        TextView txtFooterTitle = (TextView) footer.findViewById(R.id.txt_title);
        txtHeaderTitle.setText("THE HEADER!");
        txtFooterTitle.setText("THE FOOTER!");

        listView.addHeaderView(header);
        listView.addFooterView(footer);

        final SampleAdapter adapter = new SampleAdapter(this, R.id.list_item_text);
        adapter.setImages(images);
        adapter.setTitles(titles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        final List<String> sampleData = SampleData.generateSampleData();
        for (String data : sampleData) {
            adapter.add(data);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
}
