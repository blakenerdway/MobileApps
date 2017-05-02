package com.example.a127106.bibleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

/**
 * Created by 127106 on 10/31/2016.
 */
public class VerseListActivity extends AppCompatActivity {
   private Chapter _activityChapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.bible_fragment_container);
      UUID chapterID = (UUID) getIntent().getSerializableExtra("activityChapter");

      _activityChapter = Bible.get().getChapter(chapterID);

      FragmentManager fm = getSupportFragmentManager();

      Fragment myFragment = fm.findFragmentById(R.id.bible_fragment_view);

      if (myFragment == null){
         myFragment = VerseListFragment.createNewVerseList(_activityChapter.getChapterID());
         fm.beginTransaction().add(R.id.bible_fragment_view, myFragment).commit();
      }
   }

   // Create a new verse list activity
   public static Intent createNewVerseListActivity(Context c, UUID chapterID){
      Intent i = new Intent(c, VerseListActivity.class);
      i.putExtra("activityChapter", chapterID);
      return i;
   }

}
