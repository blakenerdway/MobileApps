package com.example.a127106.bibleapp;

import java.util.UUID;

/**
 * Created by 127106 on 10/28/2016.
 */
public class Verse {

    private UUID _verseID;
    private int _verseNumber;
    private int _verseStringResource;
    private boolean _verseIsRead;

    public Verse(int verseString, int verseNumber) {
        _verseID = UUID.randomUUID();
        _verseNumber = verseNumber;
        _verseStringResource = verseString;
    }

    public UUID getVerseID(){
        return _verseID;
    }

    public int getVerseNumber(){
        return _verseNumber;
    }

    public int getVerseString(){
        return _verseStringResource;
    }

    public void setVerseRead(boolean isRead){
        _verseIsRead = isRead;
    }

    public boolean getVerseRead(){
        return _verseIsRead;
    }
}
