package com.happiestminds.challenge.worddictionary.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WordDictionary {

    @Id
    private String word;

    public WordDictionary() {
    }

    public WordDictionary(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "WordDictionary{" +
                "word='" + word + '\'' +
                '}';
    }
}
