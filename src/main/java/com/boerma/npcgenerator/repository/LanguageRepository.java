package com.boerma.npcgenerator.repository;

import com.boerma.npcgenerator.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    @Query("SELECT l FROM Language l WHERE l.languageName = :languageName")
    Optional<Language> findByLanguageName(@Param("languageName") String languageName);

}
