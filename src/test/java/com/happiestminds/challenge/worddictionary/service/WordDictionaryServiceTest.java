package com.happiestminds.challenge.worddictionary.service;

import com.happiestminds.challenge.worddictionary.dao.WordDictionaryDAOImpl;
import com.happiestminds.challenge.worddictionary.exception.WordDictionaryException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class WordDictionaryServiceTest {

    @Mock
    WordDictionaryDAOImpl wordDictionaryDAO;

    @InjectMocks
    WordDictionaryServiceImpl wordDictionaryService;

    @Test
    public void testSearchWord() throws WordDictionaryException {
        Mockito.when(wordDictionaryDAO.searchWordInDB("test")).thenReturn(true);
        Boolean present = wordDictionaryService.searchWord("test");
        Assert.assertEquals(present, true);
    }

    @Test(expected = WordDictionaryException.class)
    public void testSearchWordException() throws WordDictionaryException {
        Mockito.when(wordDictionaryDAO.searchWordInDB("test")).thenThrow(WordDictionaryException.class);
        Boolean present = wordDictionaryService.searchWord("test");
    }
}
