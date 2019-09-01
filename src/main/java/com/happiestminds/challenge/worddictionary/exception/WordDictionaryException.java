package com.happiestminds.challenge.worddictionary.exception;

public class WordDictionaryException extends Exception {
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WordDictionaryException(String message) {
        this.message = message;
    }

}
