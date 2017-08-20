package com.example.searchquestionssystem.db;

import org.litepal.crud.DataSupport;

/**
 * Created by hasee on 2017/6/1.
 */

public class keyword extends DataSupport {
    String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
