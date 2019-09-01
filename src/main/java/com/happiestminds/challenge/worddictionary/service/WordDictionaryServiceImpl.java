package com.happiestminds.challenge.worddictionary.service;

import com.happiestminds.challenge.worddictionary.constants.WordDictionaryConstants;
import com.happiestminds.challenge.worddictionary.dao.WordDictionaryDAOImpl;
import com.happiestminds.challenge.worddictionary.exception.WordDictionaryException;
import com.happiestminds.challenge.worddictionary.response.WordDictionaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class WordDictionaryServiceImpl implements WordDictionaryService {

    @Autowired
    public WordDictionaryDAOImpl wordDictionaryDAO;

    @Override
    public WordDictionaryResponse createDictionary(MultipartFile file) throws WordDictionaryException {
        File normalFile = new File(file.getOriginalFilename());
        try {
            normalFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(normalFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (Exception e) {
            throw new WordDictionaryException(WordDictionaryConstants.ERROR_PARSING);
        }
        HashSet<String> hs = new HashSet<>();
        try {
            String line = null;
            InputStream fis = new FileInputStream(normalFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while ((line = br.readLine()) != null) {
                String[] words = line.replaceAll("[^a-zA-Z0-9\\s+]", "").split("\\s+");
                Arrays.stream(words).forEach(hs::add);
            }
            br.close();
            fis.close();
            WordDictionaryResponse response = wordDictionaryDAO.saveAllWords(hs);
            return response;
        } catch(Exception e) {
            throw new WordDictionaryException(WordDictionaryConstants.READ_EXCEPTION);
        } finally {
            normalFile.delete();
        }
    }

    @Override
    public Boolean searchWord(String search) throws WordDictionaryException {
        try {
            Boolean present = wordDictionaryDAO.searchWordInDB(search);
            return present;
        } catch (Exception e) {
            throw new WordDictionaryException(WordDictionaryConstants.SEARCH_EXCEPTION);
        }
    }
}
