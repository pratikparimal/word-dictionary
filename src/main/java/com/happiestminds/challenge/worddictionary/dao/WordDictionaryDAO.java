package com.happiestminds.challenge.worddictionary.dao;

import com.happiestminds.challenge.worddictionary.exception.WordDictionaryException;
import com.happiestminds.challenge.worddictionary.response.WordDictionaryResponse;

import javax.transaction.Transactional;
import java.util.HashSet;

public interface WordDictionaryDAO {
    @Transactional
    WordDictionaryResponse saveAllWords(HashSet<String> hs) throws WordDictionaryException;

    Boolean searchWordInDB(String search) throws WordDictionaryException;
}
