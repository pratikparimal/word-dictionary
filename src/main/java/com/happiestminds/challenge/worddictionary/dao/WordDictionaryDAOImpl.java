package com.happiestminds.challenge.worddictionary.dao;

import com.happiestminds.challenge.worddictionary.constants.WordDictionaryConstants;
import com.happiestminds.challenge.worddictionary.exception.WordDictionaryException;
import com.happiestminds.challenge.worddictionary.model.WordDictionary;
import com.happiestminds.challenge.worddictionary.response.WordDictionaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;

@Repository
public class WordDictionaryDAOImpl implements WordDictionaryDAO {

    @Autowired
    WordRepository wordRepository;

    @Override
    @Transactional
    public WordDictionaryResponse saveAllWords(HashSet<String> hs) throws WordDictionaryException {
        try {
            hs.stream().forEach(x -> {
                wordRepository.save(new WordDictionary(x.toLowerCase()));
            });
        } catch (Exception e ) {
            throw new WordDictionaryException(WordDictionaryConstants.ERROR_PERSISTING + e.getMessage());
        }
        return new WordDictionaryResponse(WordDictionaryConstants.SUCCESS, null);
    }

    @Override
    public Boolean searchWordInDB(String search) throws WordDictionaryException {
        try {
            Optional<WordDictionary> byId = wordRepository.findById(search);
            return byId.isPresent();
        } catch (Exception e ) {
            throw new WordDictionaryException(WordDictionaryConstants.ERROR_SEARCHING_FROM_DB + e.getMessage());
        }
    }
}
