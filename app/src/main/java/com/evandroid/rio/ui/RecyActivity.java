package com.evandroid.rio.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.evandroid.rio.R;

public class RecyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recy, menu);
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        RecyActivityFragment fragment = (RecyActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_recy);
        switch (id) {
            case R.id.recycler_view_list:
                fragment.changeLayout(RecyActivityFragment.LAYOUT_STYLE_LIST);
                break;
            case R.id.recycler_view_grid:
                fragment.changeLayout(RecyActivityFragment.LAYOUT_STYLE_GRID);
                break;
            default:
                fragment.changeLayout(RecyActivityFragment.LAYOUT_STYLE_STAGGERED);
        }

        return super.onOptionsItemSelected(item);
    }

}
