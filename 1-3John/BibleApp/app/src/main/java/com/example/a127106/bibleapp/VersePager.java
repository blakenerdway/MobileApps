package com.example.a127106.bibleapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import java.util.List;
import java.util.UUID;

/**
 * Created by 127106 on 10/29/2016.
 */
public class VersePager extends AppCompatActivity {
    private ViewPager _versePager;
    private UUID _chapterID;
    private UUID _verseID;
    private ProgressBar _progressBar;
    private List<Verse> _verses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verse_pager);

        // If this is the first time showing verses
        if (Bible.get().getFirstTime()) {
            AlertDialog.Builder helpDialog = new AlertDialog.Builder(this);
            helpDialog.setTitle("Verse Help");
            helpDialog.setMessage("Swipe left or right to see more verses.\n\n" +
                                  "A progress bar is shown at the top to indicate how " +
                                  "close to the end of the chapter you are.")
                    .setCancelable(true)
                    .setNegativeButton("Ok!", new DialogInterface.OnClickListener(){
                    		  @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            helpDialog.create();
            helpDialog.show();

            Bible.get().setFirstTime(false);
        }

        if (_chapterID == null)
            _chapterID = (UUID) getIntent().getSerializableExtra("chapterID");

        // Cache all the necessary information
        _verseID = (UUID) getIntent().getSerializableExtra("sentVerse");
        _verses = Bible.get().getAllVerses(_chapterID);
        _versePager = (ViewPager) findViewById(R.id.versePager);
        _progressBar = (ProgressBar) findViewById(R.id.progressBar);
        _progressBar.setProgress(Bible.get().getVerseProgress(_chapterID, 1));

        // Initially set the values that are necessary for the user to see
        setTitle(Bible.get().getVerse(_verseID).getVerseNumber());
        Bible.get().getVerse(_verseID).setVerseRead(true);

        _versePager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return VerseFragment.newVerseFragmentInstance(Bible.get().getAllVerses(_chapterID).get(position).getVerseID());
            }

            @Override
            public int getCount() {
                return _verses.size();
            }
        });

        // The onPageChangeListener will notify when a "new" item is displayed
        _versePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                _progressBar.setProgress(Bible.get().getVerseProgress(_chapterID, position + 1));
                Bible.get().getAllVerses(_chapterID).get(position).setVerseRead(true);
                setTitle(Bible.get().getAllVerses(_chapterID).get(position).getVerseNumber());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        for (int i = 0; i < _verses.size(); i++){
            if (_verses.get(i).getVerseID().equals(_verseID)){
                _versePager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent startNewVerseIntent(Context c, UUID id, UUID passedChapter){
        Intent i = new Intent(c, VersePager.class);
        i.putExtra("sentVerse", id);
        i.putExtra("chapterID", passedChapter);
        return i;
    }
}