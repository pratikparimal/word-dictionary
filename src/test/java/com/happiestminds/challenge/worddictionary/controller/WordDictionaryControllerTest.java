package com.happiestminds.challenge.worddictionary.controller;

import com.happiestminds.challenge.worddictionary.exception.WordDictionaryException;
import com.happiestminds.challenge.worddictionary.response.WordDictionaryResponse;
import com.happiestminds.challenge.worddictionary.service.WordDictionaryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class WordDictionaryControllerTest {

    @InjectMocks
    WordDictionaryController wordDictionaryController;

    @Mock
    WordDictionaryServiceImpl wordDictionaryService;

    /*@Test
    public void testUploadFile() throws WordDictionaryException {
        Mockito.when(wordDictionaryService.createDictionary(Mockito.any()))
                .thenReturn(new WordDictionaryResponse("created", null));
        Assert.assertEquals(wordDictionaryController.uploadFile());
    }*/

    @Test
    public void testSearchWordInDictionary() throws WordDictionaryException {
        Mockito.when(wordDictionaryService.searchWord("present")).thenReturn(true);
        ResponseEntity<WordDictionaryResponse> response = wordDictionaryController.searchWordInDictionary("present");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.FOUND);
    }

    @Test
    public void testSearchWordInDictionaryNegative() throws WordDictionaryException {
        Mockito.when(wordDictionaryService.searchWord("present")).thenReturn(false);
        ResponseEntity<WordDictionaryResponse> response = wordDictionaryController.searchWordInDictionary("present");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
