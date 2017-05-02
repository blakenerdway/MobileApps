package com.example.a127106.bibleapp;


import java.util.UUID;

/**
 * Created by 127106 on 10/28/2016.
 */
public class Chapter {
    private int  _chapterName;
    private UUID _chapterID;

    public UUID getChapterID(){
        return _chapterID;
    }

    public int getChapterName(){
        return _chapterName;
    }

    // Constructor, assign random UUID, and string chapter name
    public Chapter(int chapter) {
        _chapterID = UUID.randomUUID();
        switch(chapter){
            case(1):
                _chapterName = R.string.fJch1;
                break;
            case(2):
                _chapterName = R.string.fJch2;
                break;
            case(3):
                _chapterName = R.string.fJch3;
                break;
            case(4):
                _chapterName = R.string.fJch4;
                break;
            case(5):
                _chapterName = R.string.fJch5;
                break;
            case(6):
                _chapterName = R.string.sJch1;
                break;
            case(7):
                _chapterName = R.string.tJch1;
                break;
        }
    }
}
