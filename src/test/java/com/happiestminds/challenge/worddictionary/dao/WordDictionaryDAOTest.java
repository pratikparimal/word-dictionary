package com.happiestminds.challenge.worddictionary.dao;

import com.happiestminds.challenge.worddictionary.constants.WordDictionaryConstants;
import com.happiestminds.challenge.worddictionary.exception.WordDictionaryException;
import com.happiestminds.challenge.worddictionary.model.WordDictionary;
import com.happiestminds.challenge.worddictionary.response.WordDictionaryResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class WordDictionaryDAOTest {

    @Mock
    WordRepository wordRepository;

    @InjectMocks
    WordDictionaryDAOImpl wordDictionaryDAO;

    @Test
    public void testSaveAllWords() throws WordDictionaryException {
        Mockito.when(wordRepository.save(new WordDictionary("test"))).then(Mockito.RETURNS_SELF);
        WordDictionaryResponse response = wordDictionaryDAO.saveAllWords(new HashSet<String>());
        Assert.assertEquals(response.getSuccessMessage(), WordDictionaryConstants.SUCCESS);
    }

    @Test
    public void testSearchWordInDB() throws WordDictionaryException {
        Mockito.when(wordRepository.findById("test")).thenReturn(Optional.of(new WordDictionary()));
        Boolean present = wordDictionaryDAO.searchWordInDB("test");
        Assert.assertEquals(present, true);
    }

}
