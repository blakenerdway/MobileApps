package com.example.a127106.bibleapp;

/**
 * Created by 127106 on 10/29/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

public class VerseListFragment extends Fragment {

    private RecyclerView _verseListView;
    private VerseAdapter _adapter;
    private UUID _chapterID;


    @Override
    public void onCreate(Bundle sis) {
        super.onCreate(sis);
        _chapterID = (UUID) getArguments().getSerializable("chapterID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the fragment crime list
        View v = inflater.inflate(R.layout.fragment_verse_recycler, container, false);

        _verseListView = (RecyclerView) v.findViewById(R.id.verse_recycler_view);

        // Dynamically create new Linear layout and send this current activity (getActivity())
        _verseListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        _adapter = new VerseAdapter(Bible.get().getAllVerses(_chapterID));
        _verseListView.setAdapter(_adapter);

        // Inflate the layout for this fragment
        return v;
    }

    // Make sure the adapter knows that things have changed
    @Override
    public void onResume() {
        super.onResume();
        List<Verse> verses = Bible.get().getAllVerses(_chapterID);

        if (_adapter == null) {
            _adapter = new VerseAdapter(verses);
            _verseListView.setAdapter(_adapter);

        } else
            _adapter.notifyDataSetChanged();
    }

    // View Holder for the crime fragments
    private class VerseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView _verseNumberText;
        private TextView _versePreviewText;
        private Verse _verse;
        private ImageView _checkDone;

        public VerseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            _verseNumberText = (TextView) itemView.findViewById(R.id.verseFragString);
            _versePreviewText = (TextView) itemView.findViewById(R.id.verseTextPreview);
            _checkDone = (ImageView) itemView.findViewById(R.id.verseFragCheck);
        }

        public void bindVerseText(Verse verse) {
            _verse = verse;
            _verseNumberText.setText(_verse.getVerseNumber());
            _versePreviewText.setText(_verse.getVerseString());
            if (verse.getVerseRead())
                _checkDone.setVisibility(View.VISIBLE);
            else
                _checkDone.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {
            Intent i = VersePager.startNewVerseIntent(getActivity(), _verse.getVerseID(), _chapterID);
            startActivity(i);
        }
    }

    // Adapter, used when the recycler view needs to create a
    // new object
    private class VerseAdapter extends RecyclerView.Adapter<VerseHolder> {
        private List<Verse> _adapterVerseList;

        public VerseAdapter(List<Verse> verses) {
            _adapterVerseList = verses;
        }

        @Override
        public VerseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            // Tells adapter which layout to use
            View view = layoutInflater.inflate(R.layout.verse_list_item, parent, false);
            return new VerseHolder(view);
        }

        @Override
        public void onBindViewHolder(VerseHolder holder, int position) {
            // List.get(position) is the same as array[position]
            Verse verse = _adapterVerseList.get(position);
            holder.bindVerseText(verse);
        }

        @Override
        public int getItemCount() {
            return _adapterVerseList.size();
        }
    }

    public static VerseListFragment createNewVerseList(UUID chapterID){
        Bundle args = new Bundle();
        args.putSerializable("chapterID", chapterID);

        VerseListFragment vlFrag = new VerseListFragment();
        vlFrag.setArguments(args);
        return vlFrag;
    }
}