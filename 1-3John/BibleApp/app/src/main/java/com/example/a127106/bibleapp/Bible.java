package com.example.a127106.bibleapp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 124766 on 10/28/2016.
 */

// This is the singleton class!
public class Bible {
    private static Bible _bible;

    private List<Book> _books;
    private List<Chapter> _fjohn;
    private List<Chapter> _sjohn;
    private List<Chapter> _tjohn;
    private List<Verse> _fjohn1;
    private List<Verse> _fjohn2;
    private List<Verse> _fjohn3;
    private List<Verse> _fjohn4;
    private List<Verse> _fjohn5;
    private List<Verse> _sjohn1;
    private List<Verse> _tjohn1;

    private boolean _isFirstTimeSeen;

    public static Bible get(){
        if (_bible == null){
            _bible = new Bible();
        }
        return _bible;
    }

    // Constructor
    private Bible() {
        _isFirstTimeSeen = true;
        _books = new ArrayList<>();
        for (int i = 1; i <= 3; i++){
            Book book = new Book(i);
            _books.add(book);
        }

        _fjohn = new ArrayList<>();
        _sjohn = new ArrayList<>();
        _tjohn = new ArrayList<>();
        initializeChapters();

        _fjohn1 = new ArrayList<>();
        _fjohn2 = new ArrayList<>();
        _fjohn3 = new ArrayList<>();
        _fjohn4 = new ArrayList<>();
        _fjohn5 = new ArrayList<>();
        _sjohn1 = new ArrayList<>();
        _tjohn1 = new ArrayList<>();
        initializeVerses();
    }

    public boolean getFirstTime(){
        return _isFirstTimeSeen;
    }

    public void setFirstTime(boolean value){
        _isFirstTimeSeen = value;
    }

    // Create new chapters for the Bible
    public void initializeChapters(){
        for(int i = 1; i <= 5; i++){
            Chapter chapter = new Chapter(i);
            _fjohn.add(chapter);
        }

        _sjohn.add(new Chapter(6));
        _tjohn.add(new Chapter(7));
    }

    // Return the list of books
    public List<Book> getAllBooks(){
        return _books;
    }

    // Return the list of the chapters based on the book
    public List <Chapter> getAllChapters(UUID bookID){
        for (Book book : _books)
            if (book.getBookID().equals(bookID)) {
                if (book.getBookID().equals(_books.get(0).getBookID()))
                    return _fjohn;
                else if (book.getBookID().equals(_books.get(1).getBookID()))
                    return _sjohn;
                 else
                    return _tjohn;
            }
        return null;
    }

    // Return the verses based on the chapter
    public List<Verse> getAllVerses(UUID chapterID) {

        if (_fjohn.get(0).getChapterID().equals(chapterID))
            return _fjohn1;
        else if (_fjohn.get(1).getChapterID().equals(chapterID))
            return _fjohn2;
        else if (_fjohn.get(2).getChapterID().equals(chapterID))
            return _fjohn3;
        else if (_fjohn.get(3).getChapterID().equals(chapterID))
            return _fjohn4;
        else if (_fjohn.get(4).getChapterID().equals(chapterID))
            return _fjohn5;

        else if (_sjohn.get(0).getChapterID().equals(chapterID))
            return _sjohn1;

        else if (_tjohn.get(0).getChapterID().equals(chapterID))
            return _tjohn1;


        return null;
    }

    // Return a single book
    public Book getBook(UUID id) {
        for (Book book : _books)
            if (book.getBookID().equals(id))
                return book;
        return null;
    }

    //
    public Chapter getChapter(UUID chapterID) {
        for (Chapter chapter : _fjohn)
            if (chapter.getChapterID().equals(chapterID))
                return chapter;

            for (Chapter chapter : _sjohn)
                if (chapter.getChapterID().equals(chapterID))
                    return chapter;

            for (Chapter chapter : _tjohn)
                if (chapter.getChapterID().equals(chapterID))
                    return chapter;

        return null;
    }

    // Get the verse based on the passed in uuid
    public Verse getVerse(UUID verseID) {
        for (Verse verse : _fjohn1)
            if (verse.getVerseID().equals(verseID))
                return verse;
        for (Verse verse : _fjohn2)
            if (verse.getVerseID().equals(verseID))
                return verse;
        for (Verse verse : _fjohn3)
            if (verse.getVerseID().equals(verseID))
                return verse;
        for (Verse verse : _fjohn4)
            if (verse.getVerseID().equals(verseID))
                return verse;
        for (Verse verse : _fjohn5)
            if (verse.getVerseID().equals(verseID))
                return verse;

        for (Verse verse : _sjohn1)
            if (verse.getVerseID().equals(verseID))
                return verse;


        for (Verse verse : _tjohn1)
            if (verse.getVerseID().equals(verseID))
                return verse;

        return null;
    }


    // Get total of book read percentage
    public int getBookReadPercentage(UUID bookID) {
        int totalRead = 0;
        int totalVerses = 0;

        for (Book book : _books)
            if (book.getBookID().equals(bookID)) {
                // If book of 1 john, loop through all chapters
                if (book.getBookName() == R.string.fJohn) {
                    for (Chapter chapter : _fjohn) {
                        if (chapter.getChapterID().equals(_fjohn.get(0).getChapterID())) {
                            totalVerses += _fjohn1.size();
                            for (Verse verse : _fjohn1)
                                if (verse.getVerseRead())
                                    totalRead++;

                        } else if (chapter.getChapterID().equals(_fjohn.get(1).getChapterID())) {
                            totalVerses += _fjohn2.size();
                            for (Verse verse : _fjohn2)
                                if (verse.getVerseRead())
                                    totalRead++;

                        } else if (chapter.getChapterID().equals(_fjohn.get(2).getChapterID())) {
                            totalVerses += _fjohn3.size();
                            for (Verse verse : _fjohn3)
                                if (verse.getVerseRead())
                                    totalRead++;

                        } else if (chapter.getChapterID().equals(_fjohn.get(3).getChapterID())) {
                            totalVerses += _fjohn4.size();
                            for (Verse verse : _fjohn4)
                                if (verse.getVerseRead())
                                    totalRead++;


                        } else if (chapter.getChapterID().equals(_fjohn.get(4).getChapterID())) {
                            totalVerses += _fjohn5.size();
                            for (Verse verse : _fjohn5)
                                if (verse.getVerseRead())
                                    totalRead++;
                        }
                    }

                // If 2 John, just get the single chapter
                } else if (book.getBookName() == R.string.sJohn) {
                    totalVerses += _sjohn1.size();
                    for (Verse verse : _sjohn1)
                        if (verse.getVerseRead())
                            totalRead++;

                // If 3 John, just get the single chapter
                } else {
                    totalVerses += _tjohn1.size();
                    for (Verse verse : _tjohn1)
                        if (verse.getVerseRead())
                            totalRead++;
                }
            }
        return  (int) (100 * ( (float) totalRead / (float) totalVerses));
    }

    // Return the percentage of the chapter read
    public float getChapterReadPercentage(UUID chapterID){
        int totalRead = 0;
        int totalVerses = 0;

        // Check the book's chapter's id's for the corresponding UUID

        if(_fjohn.get(0).getChapterID().equals(chapterID)) {
            totalVerses = _fjohn1.size();
            for (Verse verse : _fjohn1)
                if (verse.getVerseRead())
                    totalRead++;
        }

        else if (_fjohn.get(1).getChapterID().equals(chapterID)) {
            totalVerses = _fjohn2.size();
            for (Verse verse : _fjohn2)
                if (verse.getVerseRead())
                    totalRead++;
        }

        else if (_fjohn.get(2).getChapterID().equals(chapterID)) {
            totalVerses = _fjohn3.size();
            for (Verse verse : _fjohn3)
                if (verse.getVerseRead())
                    totalRead++;
        }

        else if(_fjohn.get(3).getChapterID().equals(chapterID)){
            totalVerses = _fjohn4.size();
            for (Verse verse : _fjohn4)
                if (verse.getVerseRead())
                    totalRead++;
        }

        else if (_fjohn.get(4).getChapterID().equals(chapterID)) {
            totalVerses = _fjohn5.size();
            for (Verse verse : _fjohn5)
                if (verse.getVerseRead())
                    totalRead++;
        }

        else if (_sjohn.get(0).getChapterID().equals(chapterID)) {
            totalVerses = _sjohn1.size();
            for (Verse verse : _sjohn1)
                if (verse.getVerseRead())
                    totalRead++;
        }

        else if (_tjohn.get(0).getChapterID().equals(chapterID)) {
            totalVerses = _tjohn1.size();
            for (Verse verse : _tjohn1)
                if (verse.getVerseRead())
                    totalRead++;
        }

        return (int) (100 *  ( (float) totalRead / (float) totalVerses));
    }

    public int getVerseProgress(UUID chapterID, int position){
        List<Verse> verseList = null;

        if (_fjohn.get(0).getChapterID().equals(chapterID))
            verseList = _fjohn1;
        else if (_fjohn.get(1).getChapterID().equals(chapterID))
            verseList = _fjohn2;
        else if (_fjohn.get(2).getChapterID().equals(chapterID))
            verseList = _fjohn3;
        else if (_fjohn.get(3).getChapterID().equals(chapterID))
            verseList = _fjohn4;
        else if (_fjohn.get(4).getChapterID().equals(chapterID))
            verseList = _fjohn5;

        else if (_sjohn.get(0).getChapterID().equals(chapterID))
            verseList = _sjohn1;
        else if (_tjohn.get(0).getChapterID().equals(chapterID))
            verseList = _tjohn1;

        return (int) (100 * ((float) position / (float) verseList.size()));
    }

    // Initialize the verses with their verse numbers and actual verse text
    public void initializeVerses(){
        int verseStringText = -99;
        int verseNumber = -99;
        for (int i = 1; i <= 10; i++){
            switch (i) {
                case (1):
                    verseNumber = R.string.fJ1v1;
                    verseStringText = R.string.fjohn11;
                    break;
                case (2):
                    verseNumber = R.string.fJ1v2;
                    verseStringText = R.string.fjohn12;
                    break;
                case (3):
                    verseNumber = R.string.fJ1v3;
                    verseStringText = R.string.fjohn13;
                    break;
                case (4):
                    verseNumber = R.string.fJ1v4;
                    verseStringText = R.string.fjohn14;
                    break;
                case (5):
                    verseNumber = R.string.fJ1v5;
                    verseStringText = R.string.fjohn15;
                    break;
                case (6):
                    verseNumber = R.string.fJ1v6;
                    verseStringText = R.string.fjohn16;
                    break;
                case (7):
                    verseNumber = R.string.fJ1v7;
                    verseStringText = R.string.fjohn17;
                    break;
                case (8):
                    verseNumber = R.string.fJ1v8;
                    verseStringText = R.string.fjohn18;
                    break;
                case (9):
                    verseNumber = R.string.fJ1v9;
                    verseStringText = R.string.fjohn19;
                    break;
                case (10):
                    verseNumber = R.string.fJ1v10;
                    verseStringText = R.string.fjohn110;
                    break;
                default:
                    break;
            }
            _fjohn1.add(new Verse(verseStringText, verseNumber));
        }

        for (int i = 1; i <= 29; i++){
            switch (i) {
                case (1):
                    verseNumber = R.string.fJ2v1;
                    verseStringText = R.string.fjohn21;
                    break;
                case (2):
                    verseNumber = R.string.fJ2v2;
                    verseStringText = R.string.fjohn22;
                    break;
                case (3):
                    verseNumber = R.string.fJ2v3;
                    verseStringText = R.string.fjohn23;
                    break;
                case (4):
                    verseNumber = R.string.fJ2v4;
                    verseStringText = R.string.fjohn24;
                    break;
                case (5):
                    verseNumber = R.string.fJ2v5;
                    verseStringText = R.string.fjohn25;
                    break;
                case (6):
                    verseNumber = R.string.fJ2v6;
                    verseStringText = R.string.fjohn26;
                    break;
                case (7):
                    verseNumber = R.string.fJ2v7;
                    verseStringText = R.string.fjohn27;
                    break;
                case (8):
                    verseNumber = R.string.fJ2v8;
                    verseStringText = R.string.fjohn28;
                    break;
                case (9):
                    verseNumber = R.string.fJ2v9;
                    verseStringText = R.string.fjohn29;
                    break;
                case (10):
                    verseNumber = R.string.fJ2v10;
                    verseStringText = R.string.fjohn210;
                    break;
                case (11):
                    verseNumber = R.string.fJ2v11;
                    verseStringText = R.string.fjohn211;
                    break;
                case (12):
                    verseNumber = R.string.fJ2v12;
                    verseStringText = R.string.fjohn212;
                    break;
                case (13):
                    verseNumber = R.string.fJ2v13;
                    verseStringText = R.string.fjohn213;
                    break;
                case (14):
                    verseNumber = R.string.fJ2v14;
                    verseStringText = R.string.fjohn214;
                    break;
                case (15):
                    verseNumber = R.string.fJ2v15;
                    verseStringText = R.string.fjohn215;
                    break;
                case (16):
                    verseNumber = R.string.fJ2v16;
                    verseStringText = R.string.fjohn216;
                    break;
                case (17):
                    verseNumber = R.string.fJ2v17;
                    verseStringText = R.string.fjohn217;
                    break;
                case (18):
                    verseNumber = R.string.fJ2v18;
                    verseStringText = R.string.fjohn218;
                    break;
                case (19):
                    verseNumber = R.string.fJ2v19;
                    verseStringText = R.string.fjohn219;
                    break;
                case (20):
                    verseNumber = R.string.fJ2v20;
                    verseStringText = R.string.fjohn220;
                    break;
                case (21):
                    verseNumber = R.string.fJ2v21;
                    verseStringText = R.string.fjohn221;
                    break;
                case (22):
                    verseNumber = R.string.fJ2v22;
                    verseStringText = R.string.fjohn222;
                    break;
                case (23):
                    verseNumber = R.string.fJ2v23;
                    verseStringText = R.string.fjohn213;
                    break;
                case (24):
                    verseNumber = R.string.fJ2v24;
                    verseStringText = R.string.fjohn214;
                    break;
                case (25):
                    verseNumber = R.string.fJ2v25;
                    verseStringText = R.string.fjohn215;
                    break;
                case (26):
                    verseNumber = R.string.fJ2v26;
                    verseStringText = R.string.fjohn216;
                    break;
                case (27):
                    verseNumber = R.string.fJ2v27;
                    verseStringText = R.string.fjohn217;
                    break;
                case (28):
                    verseNumber = R.string.fJ2v28;
                    verseStringText = R.string.fjohn218;
                    break;
                case (29):
                    verseNumber = R.string.fJ2v29;
                    verseStringText = R.string.fjohn219;
                    break;
                default:
                    break;
            }
            _fjohn2.add(new Verse(verseStringText, verseNumber));
        }

        for (int i = 1; i <= 24; i++){
            switch (i) {
                case (1):
                    verseNumber = R.string.fJ3v1;
                    verseStringText = R.string.fjohn31;
                    break;
                case (2):
                    verseNumber = R.string.fJ3v2;
                    verseStringText = R.string.fjohn32;
                    break;
                case (3):
                    verseNumber = R.string.fJ3v3;
                    verseStringText = R.string.fjohn33;
                    break;
                case (4):
                    verseNumber = R.string.fJ3v4;
                    verseStringText = R.string.fjohn34;
                    break;
                case (5):
                    verseNumber = R.string.fJ3v5;
                    verseStringText = R.string.fjohn35;
                    break;
                case (6):
                    verseNumber = R.string.fJ3v6;
                    verseStringText = R.string.fjohn36;
                    break;
                case (7):
                    verseNumber = R.string.fJ3v7;
                    verseStringText = R.string.fjohn37;
                    break;
                case (8):
                    verseNumber = R.string.fJ3v8;
                    verseStringText = R.string.fjohn38;
                    break;
                case (9):
                    verseNumber = R.string.fJ3v9;
                    verseStringText = R.string.fjohn39;
                    break;
                case (10):
                    verseNumber = R.string.fJ3v10;
                    verseStringText = R.string.fjohn310;
                    break;
                case (11):
                    verseNumber = R.string.fJ3v11;
                    verseStringText = R.string.fjohn311;
                    break;
                case (12):
                    verseNumber = R.string.fJ3v12;
                    verseStringText = R.string.fjohn312;
                    break;
                case (13):
                    verseNumber = R.string.fJ3v13;
                    verseStringText = R.string.fjohn313;
                    break;
                case (14):
                    verseNumber = R.string.fJ3v14;
                    verseStringText = R.string.fjohn314;
                    break;
                case (15):
                    verseNumber = R.string.fJ3v15;
                    verseStringText = R.string.fjohn315;
                    break;
                case (16):
                    verseNumber = R.string.fJ3v16;
                    verseStringText = R.string.fjohn316;
                    break;
                case (17):
                    verseNumber = R.string.fJ3v17;
                    verseStringText = R.string.fjohn317;
                    break;
                case (18):
                    verseNumber = R.string.fJ3v18;
                    verseStringText = R.string.fjohn318;
                    break;
                case (19):
                    verseNumber = R.string.fJ3v19;
                    verseStringText = R.string.fjohn319;
                    break;
                case (20):
                    verseNumber = R.string.fJ3v20;
                    verseStringText = R.string.fjohn320;
                    break;
                case (21):
                    verseNumber = R.string.fJ3v21;
                    verseStringText = R.string.fjohn321;
                    break;
                case (22):
                    verseNumber = R.string.fJ3v22;
                    verseStringText = R.string.fjohn322;
                    break;
                case (23):
                    verseNumber = R.string.fJ3v23;
                    verseStringText = R.string.fjohn323;
                    break;
                case (24):
                    verseNumber = R.string.fJ3v24;
                    verseStringText = R.string.fjohn324;
                    break;
                default:
                    break;
            }
            _fjohn3.add(new Verse(verseStringText, verseNumber));
        }

        for (int i = 1; i <= 21; i++){
            switch (i) {
                case (1):
                    verseNumber = R.string.fJ4v1;
                    verseStringText = R.string.fjohn41;
                    break;
                case (2):
                    verseNumber = R.string.fJ4v2;
                    verseStringText = R.string.fjohn42;
                    break;
                case (3):
                    verseNumber = R.string.fJ4v3;
                    verseStringText = R.string.fjohn43;
                    break;
                case (4):
                    verseNumber = R.string.fJ4v4;
                    verseStringText = R.string.fjohn44;
                    break;
                case (5):
                    verseNumber = R.string.fJ4v5;
                    verseStringText = R.string.fjohn45;
                    break;
                case (6):
                    verseNumber = R.string.fJ4v6;
                    verseStringText = R.string.fjohn46;
                    break;
                case (7):
                    verseNumber = R.string.fJ4v7;
                    verseStringText = R.string.fjohn47;
                    break;
                case (8):
                    verseNumber = R.string.fJ4v8;
                    verseStringText = R.string.fjohn48;
                    break;
                case (9):
                    verseNumber = R.string.fJ4v9;
                    verseStringText = R.string.fjohn49;
                    break;
                case (10):
                    verseNumber = R.string.fJ4v10;
                    verseStringText = R.string.fjohn410;
                    break;
                case (11):
                    verseNumber = R.string.fJ4v11;
                    verseStringText = R.string.fjohn411;
                    break;
                case (12):
                    verseNumber = R.string.fJ4v12;
                    verseStringText = R.string.fjohn412;
                    break;
                case (13):
                    verseNumber = R.string.fJ4v13;
                    verseStringText = R.string.fjohn413;
                    break;
                case (14):
                    verseNumber = R.string.fJ4v14;
                    verseStringText = R.string.fjohn414;
                    break;
                case (15):
                    verseNumber = R.string.fJ4v15;
                    verseStringText = R.string.fjohn415;
                    break;
                case (16):
                    verseNumber = R.string.fJ4v16;
                    verseStringText = R.string.fjohn416;
                    break;
                case (17):
                    verseNumber = R.string.fJ4v17;
                    verseStringText = R.string.fjohn417;
                    break;
                case (18):
                    verseNumber = R.string.fJ4v18;
                    verseStringText = R.string.fjohn418;
                    break;
                case (19):
                    verseNumber = R.string.fJ4v19;
                    verseStringText = R.string.fjohn419;
                    break;
                case (20):
                    verseNumber = R.string.fJ4v20;
                    verseStringText = R.string.fjohn420;
                    break;
                case (21):
                    verseNumber = R.string.fJ4v21;
                    verseStringText = R.string.fjohn421;
                    break;
                default:
                    break;
            }
            _fjohn4.add(new Verse(verseStringText, verseNumber));
        }

        for (int i = 1; i <= 21; i++){
            switch (i) {
                case (1):
                    verseNumber = R.string.fJ5v1;
                    verseStringText = R.string.fjohn51;
                    break;
                case (2):
                    verseNumber = R.string.fJ5v2;
                    verseStringText = R.string.fjohn52;
                    break;
                case (3):
                    verseNumber = R.string.fJ5v3;
                    verseStringText = R.string.fjohn53;
                    break;
                case (4):
                    verseNumber = R.string.fJ5v4;
                    verseStringText = R.string.fjohn54;
                    break;
                case (5):
                    verseNumber = R.string.fJ5v5;
                    verseStringText = R.string.fjohn55;
                    break;
                case (6):
                    verseNumber = R.string.fJ5v6;
                    verseStringText = R.string.fjohn56;
                    break;
                case (7):
                    verseNumber = R.string.fJ5v7;
                    verseStringText = R.string.fjohn57;
                    break;
                case (8):
                    verseNumber = R.string.fJ5v8;
                    verseStringText = R.string.fjohn58;
                    break;
                case (9):
                    verseNumber = R.string.fJ5v9;
                    verseStringText = R.string.fjohn59;
                    break;
                case (10):
                    verseNumber = R.string.fJ5v10;
                    verseStringText = R.string.fjohn510;
                    break;
                case (11):
                    verseNumber = R.string.fJ5v11;
                    verseStringText = R.string.fjohn511;
                    break;
                case (12):
                    verseNumber = R.string.fJ5v12;
                    verseStringText = R.string.fjohn512;
                    break;
                case (13):
                    verseNumber = R.string.fJ5v13;
                    verseStringText = R.string.fjohn513;
                    break;
                case (14):
                    verseNumber = R.string.fJ5v14;
                    verseStringText = R.string.fjohn514;
                    break;
                case (15):
                    verseNumber = R.string.fJ5v15;
                    verseStringText = R.string.fjohn515;
                    break;
                case (16):
                    verseNumber = R.string.fJ5v16;
                    verseStringText = R.string.fjohn516;
                    break;
                case (17):
                    verseNumber = R.string.fJ5v17;
                    verseStringText = R.string.fjohn517;
                    break;
                case (18):
                    verseNumber = R.string.fJ5v18;
                    verseStringText = R.string.fjohn518;
                    break;
                case (19):
                    verseNumber = R.string.fJ5v19;
                    verseStringText = R.string.fjohn519;
                    break;
                case (20):
                    verseNumber = R.string.fJ5v20;
                    verseStringText = R.string.fjohn520;
                    break;
                case (21):
                    verseNumber = R.string.fJ5v21;
                    verseStringText = R.string.fjohn521;
                    break;
                default:
                    break;
            }
            _fjohn5.add(new Verse(verseStringText, verseNumber));
        }

        for (int i = 1; i <= 13; i++){
            switch (i) {
                case (1):
                    verseNumber = R.string.sJv1;
                    verseStringText = R.string.sjohn1;
                    break;
                case (2):
                    verseNumber = R.string.sJv2;
                    verseStringText = R.string.sjohn2;
                    break;
                case (3):
                    verseNumber = R.string.sJv3;
                    verseStringText = R.string.sjohn3;
                    break;
                case (4):
                    verseNumber = R.string.sJv4;
                    verseStringText = R.string.sjohn4;
                    break;
                case (5):
                    verseNumber = R.string.sJv5;
                    verseStringText = R.string.sjohn5;
                    break;
                case (6):
                    verseNumber = R.string.sJv6;
                    verseStringText = R.string.sjohn6;
                    break;
                case (7):
                    verseNumber = R.string.sJv7;
                    verseStringText = R.string.sjohn7;
                    break;
                case (8):
                    verseNumber = R.string.sJv8;
                    verseStringText = R.string.sjohn8;
                    break;
                case (9):
                    verseNumber = R.string.sJv9;
                    verseStringText = R.string.sjohn9;
                    break;
                case (10):
                    verseNumber = R.string.sJv10;
                    verseStringText = R.string.sjohn10;
                    break;
                case (11):
                    verseNumber = R.string.sJv11;
                    verseStringText = R.string.sjohn11;
                    break;
                case (12):
                    verseNumber = R.string.sJv12;
                    verseStringText = R.string.sjohn12;
                    break;
                case (13):
                    verseNumber = R.string.sJv13;
                    verseStringText = R.string.sjohn13;
                    break;
                default:
                    break;
            }
            _sjohn1.add(new Verse(verseStringText, verseNumber));
        }

        for (int i = 1; i <= 14; i++){
            switch (i) {
                case (1):
                    verseNumber = R.string.tJv1;
                    verseStringText = R.string.tjohn1;
                    break;
                case (2):
                    verseNumber = R.string.tJv2;
                    verseStringText = R.string.tjohn2;
                    break;
                case (3):
                    verseNumber = R.string.tJv3;
                    verseStringText = R.string.tjohn3;
                    break;
                case (4):
                    verseNumber = R.string.tJv4;
                    verseStringText = R.string.tjohn4;
                    break;
                case (5):
                    verseNumber = R.string.tJv5;
                    verseStringText = R.string.tjohn5;
                    break;
                case (6):
                    verseNumber = R.string.tJv6;
                    verseStringText = R.string.tjohn6;
                    break;
                case (7):
                    verseNumber = R.string.tJv7;
                    verseStringText = R.string.tjohn7;
                    break;
                case (8):
                    verseNumber = R.string.tJv8;
                    verseStringText = R.string.tjohn8;
                    break;
                case (9):
                    verseNumber = R.string.tJv9;
                    verseStringText = R.string.tjohn9;
                    break;
                case (10):
                    verseNumber = R.string.tJv10;
                    verseStringText = R.string.tjohn10;
                    break;
                case (11):
                    verseNumber = R.string.tJv11;
                    verseStringText = R.string.tjohn11;
                    break;
                case (12):
                    verseNumber = R.string.tJv12;
                    verseStringText = R.string.tjohn12;
                    break;
                case (13):
                    verseNumber = R.string.tJv13;
                    verseStringText = R.string.tjohn13;
                    break;
                case(14):
                    verseNumber = R.string.tJv14;
                    verseStringText = R.string.tjohn14;
                default:
                    break;
            }
            _tjohn1.add(new Verse(verseStringText, verseNumber));
        }
    }


}
