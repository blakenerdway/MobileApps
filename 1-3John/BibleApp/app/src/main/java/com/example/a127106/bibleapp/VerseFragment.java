package com.example.a127106.bibleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by 127106 on 10/29/2016.
 *
 *
 * A simple {@link Fragment} subclass.
 */
public class VerseFragment extends Fragment{

    TextView _verseText;
    Verse _verse;
    Chapter _chapter;

    @Override
    public void onCreate(Bundle sis) {
        super.onCreate(sis);
        UUID verseID = (UUID) getArguments().getSerializable("verseID");
        _verse = Bible.get().getVerse(verseID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_verse_text, container, false);

        _verseText = (TextView) v.findViewById(R.id.verseText);

        _verseText.setText(_verse.getVerseString());

        return v;
    }

    public static VerseFragment newVerseFragmentInstance(UUID verseID) {
        Bundle args = new Bundle();
        args.putSerializable("verseID", verseID);

        VerseFragment fragment = new VerseFragment();
        fragment.setArguments(args);
        return fragment;

    }
}
