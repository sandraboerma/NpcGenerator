package com.boerma.npcgenerator.repository;

import com.boerma.npcgenerator.domain.Language;
import com.boerma.npcgenerator.domain.RaceLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RaceLanguageRepository extends JpaRepository<RaceLanguage, Integer> {

    @Query("SELECT l FROM Language l JOIN RaceLanguage rl ON l.id = rl.languageId WHERE rl.raceId = :raceId")
    Language findLanguageByRaceId(@Param("raceId") int raceId);

}
