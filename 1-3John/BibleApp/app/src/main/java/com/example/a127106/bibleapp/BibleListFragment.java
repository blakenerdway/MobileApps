package com.example.a127106.bibleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 127106 on 10/31/2016.
 */
public class BibleListFragment extends Fragment {

    private RecyclerView _bibleListView;
    private BibleAdapter _bibleAdapter;

    @Override
    public void onCreate(Bundle sis) {
        super.onCreate(sis);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the fragment crime list
        View v = inflater.inflate(R.layout.fragment_book_recycler, container, false);

        _bibleListView = (RecyclerView) v.findViewById(R.id.bible_recycler_view);

        // Dynamically create new Linear layout and send this current activity (getActivity())
        _bibleListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get means get instance of the singleton
        _bibleAdapter = new BibleAdapter(Bible.get().getAllBooks());
        _bibleListView.setAdapter(_bibleAdapter);

        // Inflate the layout for this fragment
        return v;
    }

    // Make sure the adapter knows that there's data to update
    @Override
    public void onResume() {
        super.onResume();
        if (_bibleAdapter == null) {
            _bibleAdapter = new BibleAdapter(Bible.get().getAllBooks());
            _bibleListView.setAdapter(_bibleAdapter);
        } else
            _bibleAdapter.notifyDataSetChanged();
    }

    // View Holder for the crime fragments
    private class BibleHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView _bookTitleText;
        private TextView _percentage;

        private Book _book;

        public BibleHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            _bookTitleText = (TextView) itemView.findViewById(R.id.bibleFragString);
            _percentage = (TextView) itemView.findViewById(R.id.bookPercentageRead);
        }

        public void bindCrimeTextData(Book book) {
            _book = book;
            _percentage.setText(Bible.get().getBookReadPercentage(book.getBookID()) + "%");
            _bookTitleText.setText(_book.getBookName());
        }

        @Override
        public void onClick(View v) {
            // If there's more than 1 chapter, show chapter list
            if (Bible.get().getAllChapters(_book.getBookID()).size() > 1) {
                Intent i = ChapterListActivity.createNewChapterListActivity(getActivity(), _book.getBookID());
                startActivity(i);
            }
            // If there's only 1 chapter, show the verses
            else {
                Intent i = VerseListActivity.createNewVerseListActivity(getActivity(),
                        Bible.get().getAllChapters(_book.getBookID()).get(0).getChapterID());
                startActivity(i);
            }
        }
    }

    // Adapter, used when the recycler view needs to create a
    // new object
    private class BibleAdapter extends RecyclerView.Adapter<BibleHolder> {
        private List<Book> _bookAdapter;

        public BibleAdapter(List<Book> books) {
            _bookAdapter = books;
        }

        @Override
        public BibleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            // Tells adapter which layout to use
            View view = layoutInflater.inflate(R.layout.book_list_item, parent, false);
            return new BibleHolder(view);
        }

        @Override
        public void onBindViewHolder(BibleHolder holder, int position) {
            // List.get(position) is the same as array[position]
            Book books = _bookAdapter.get(position);
            holder.bindCrimeTextData(books);
        }

        @Override
        public int getItemCount() {
            return _bookAdapter.size();
        }
    }
}

