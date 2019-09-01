package com.happiestminds.challenge.worddictionary.controller;

import com.happiestminds.challenge.worddictionary.constants.WordDictionaryConstants;
import com.happiestminds.challenge.worddictionary.response.WordDictionaryResponse;
import com.happiestminds.challenge.worddictionary.service.WordDictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class WordDictionaryController {

    @Autowired
    WordDictionaryServiceImpl wordDictionaryService;

    @PostMapping("/createDictionary/selectFile")
    public ResponseEntity<WordDictionaryResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(new WordDictionaryResponse(
                    null, WordDictionaryConstants.NO_FILE),
                    HttpStatus.NO_CONTENT);
        }
        try {
            WordDictionaryResponse response = wordDictionaryService.createDictionary(file);
            return new ResponseEntity<>(new WordDictionaryResponse(
                    WordDictionaryConstants.DICTIONARY_CREATED, null),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new WordDictionaryResponse(
                    null, WordDictionaryConstants.DICTIONARY_CREATION_ERROR),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/searchWord")
    public ResponseEntity<WordDictionaryResponse> searchWordInDictionary(@RequestParam String search) {
        try {
            Boolean present = wordDictionaryService.searchWord(search);
            if (present) {
                return new ResponseEntity<>(new WordDictionaryResponse(
                        search + WordDictionaryConstants.PRESENT, null),
                        HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(new WordDictionaryResponse(
                        search + WordDictionaryConstants.NOT_PRESENT, null),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new WordDictionaryResponse(
                    null, WordDictionaryConstants.ERROR_SEARCHING),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
