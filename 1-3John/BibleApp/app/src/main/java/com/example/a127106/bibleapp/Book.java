package com.example.a127106.bibleapp;

import java.util.UUID;

/**
 * Created by 127106 on 10/28/2016.
 */
public class Book {
    private UUID _bookID;
    private int _bookName;

    public Book(int bookNumber) {
        _bookID = UUID.randomUUID();

        switch (bookNumber) {
            case (1):
                _bookName = R.string.fJohn;
                break;
            case (2):
                _bookName = R.string.sJohn;
                break;
            case (3):
                _bookName = R.string.tJohn;
                break;
        }
    }

    public UUID getBookID(){
        return _bookID;
    }


    public int getBookName(){
        return _bookName;
    }
}