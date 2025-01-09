package com.boerma.npcgenerator.service;

import com.boerma.npcgenerator.domain.Language;
import com.boerma.npcgenerator.domain.Npc;
import com.boerma.npcgenerator.domain.Race;
import com.boerma.npcgenerator.domain.SocialStatus;
import com.boerma.npcgenerator.repository.LanguageRepository;
import com.boerma.npcgenerator.repository.RaceLanguageRepository;
import com.boerma.npcgenerator.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class LanguageAssignmentService {

    private final RaceLanguageRepository raceLanguageRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageAssignmentService(RaceLanguageRepository raceLanguageRepository, LanguageRepository languageRepository) {
        this.raceLanguageRepository = raceLanguageRepository;
        this.languageRepository = languageRepository;
    }

    public void assignLanguagesToNpc(Npc npc, Race race, SocialStatus socialStatus) {
        Set<Language> assignedLanguages = new HashSet<>();

        Language raceLanguage = raceLanguageRepository.findLanguageByRaceId(race.getId());
        assignedLanguages.add(raceLanguage);

        int totalLanguages = switch (socialStatus.getId()) {
            case 1 -> 1;
            case 2,3 -> 2;
            case 4,5,6 -> 3;
            default -> throw new IllegalArgumentException("Unknown social status ID: " + socialStatus.getId());
        };

        if (totalLanguages > 1) {
            Language commonLanguage = languageRepository.findByLanguageName("Common")
                    .orElseThrow(() -> new IllegalArgumentException("Language not found: Common"));
            assignedLanguages.add(commonLanguage);
        }

        if (totalLanguages > 2) {
            List<Language> allLanguages = languageRepository.findAll();
            allLanguages.removeAll(assignedLanguages);

            ValidationUtility.validateNotEmpty("allLanguages", allLanguages);

            Random random = new Random();
            while (assignedLanguages.size() < totalLanguages) {
                assignedLanguages.add(allLanguages.get(random.nextInt(allLanguages.size())));
            }
        }

        npc.getLanguages().addAll(assignedLanguages);
    }
}
