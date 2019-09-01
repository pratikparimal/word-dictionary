package com.happiestminds.challenge.worddictionary.dao;

import com.happiestminds.challenge.worddictionary.model.WordDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<WordDictionary, String> {
}
