package com.example.a127106.bibleapp;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

/**
 * Created by 127106 on 10/31/2016.
 */
public class ChapterListFragment extends Fragment {
    private RecyclerView _chapterListView;
    private ChapterAdapter _chapterAdapter;
    private UUID _bookID;

    @Override
    public void onCreate(Bundle sis){
        super.onCreate(sis);
        _bookID = (UUID) getArguments().getSerializable("bookID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the fragment crime list
        View v = inflater.inflate(R.layout.fragment_chapter_recycler, container, false);

        _chapterListView = (RecyclerView) v.findViewById(R.id.chapter_recycler_view);

        // Dynamically create new Linear layout and send this current activity (getActivity())
        _chapterListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get means get instance of the singleton
        _chapterAdapter = new ChapterAdapter(Bible.get().getAllChapters(_bookID));
        _chapterListView.setAdapter(_chapterAdapter);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume(){
        super.onResume();

        if (_chapterAdapter == null){
            _chapterAdapter = new ChapterAdapter(Bible.get().getAllChapters(_bookID));
            _chapterListView.setAdapter(_chapterAdapter);
        }

        else
            _chapterAdapter.notifyDataSetChanged();
    }

    // View Holder for the crime fragments
    private class ChapterHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView _chapterTextView;
        private TextView _chapterPercentage;

        private Chapter _chapter;

        public ChapterHolder (View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            _chapterPercentage = (TextView) itemView.findViewById(R.id.chapterPercentageRead);
            _chapterTextView = (TextView) itemView.findViewById(R.id.chapterFragString);
        }

        public void bindChapterTextData(Chapter chapter){
            _chapter = chapter;
            _chapterPercentage.setText(Bible.get().getChapterReadPercentage(_chapter.getChapterID()) + "%");
            _chapterTextView.setText(_chapter.getChapterName());
        }

        @Override
        public void onClick(View v){
            Intent i = VerseListActivity.createNewVerseListActivity(getActivity(), _chapter.getChapterID());
            startActivity(i);
        }
    }

    // Adapter, used when the recycler view needs to create a
    // new object
    private class ChapterAdapter extends RecyclerView.Adapter<ChapterHolder>{
        private List<Chapter> _chapterAdapterList;

        public ChapterAdapter(List<Chapter> chapters){
            _chapterAdapterList = chapters;
        }

        @Override
        public ChapterHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            // Tells adapter which layout to use
            View view = layoutInflater.inflate(R.layout.chapter_list_item, parent, false);
            return new ChapterHolder(view);
        }

        @Override
        public void onBindViewHolder(ChapterHolder holder, int position){
            // List.get(position) is the same as array[position]
            Chapter chapter = _chapterAdapterList.get(position);
            holder.bindChapterTextData(chapter);
        }

        @Override
        public int getItemCount(){
            return _chapterAdapterList.size();
        }
    }

    public static ChapterListFragment createNewChapterList(UUID bookID)
    {
        Bundle args = new Bundle();
        args.putSerializable("bookID", bookID);

        ChapterListFragment clFrag = new ChapterListFragment();
        clFrag.setArguments(args);
        return clFrag;
    }
}