package com.happiestminds.challenge.worddictionary.service;

import com.happiestminds.challenge.worddictionary.exception.WordDictionaryException;
import com.happiestminds.challenge.worddictionary.response.WordDictionaryResponse;
import org.springframework.web.multipart.MultipartFile;

public interface WordDictionaryService {
    WordDictionaryResponse createDictionary(MultipartFile file) throws WordDictionaryException;

    Boolean searchWord(String search) throws WordDictionaryException;
}
