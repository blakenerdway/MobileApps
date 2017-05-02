package com.example.a127106.bibleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

/**
 * Created by Dan on 10/31/2016.
 */
public class ChapterListActivity extends AppCompatActivity {
    private UUID bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bible_fragment_container);

        bookId = (UUID) getIntent().getSerializableExtra("bookID");

        FragmentManager fm = getSupportFragmentManager();

        Fragment myFragment = fm.findFragmentById(R.id.bible_fragment_view);

        if (myFragment == null){
            myFragment = ChapterListFragment.createNewChapterList(bookId);
            fm.beginTransaction().add(R.id.bible_fragment_view, myFragment).commit();
        }
    }

    public static Intent createNewChapterListActivity(Context c, UUID bookId) {
        Intent i = new Intent(c, ChapterListActivity.class);
        i.putExtra("bookID", bookId);

        return i;
    }
}
