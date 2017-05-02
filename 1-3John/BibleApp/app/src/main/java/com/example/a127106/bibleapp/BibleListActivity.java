package com.example.a127106.bibleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dan on 10/31/2016.
 */
public class BibleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bible_fragment_container);

        FragmentManager fm = getSupportFragmentManager();

        Fragment myFragment = fm.findFragmentById(R.id.bible_fragment_view);

        if (myFragment == null){
            myFragment = new BibleListFragment();
            fm.beginTransaction().add(R.id.bible_fragment_view, myFragment).commit();
        }
    }
}
