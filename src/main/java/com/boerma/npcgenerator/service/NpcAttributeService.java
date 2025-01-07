package com.boerma.npcgenerator.service;

import com.boerma.npcgenerator.domain.*;
import com.boerma.npcgenerator.repository.*;
import com.boerma.npcgenerator.utility.ValidationUtility;
import com.boerma.npcgenerator.utility.RandomizationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NpcAttributeService {

    private final Map<String, List<?>> attributes = new HashMap<>();
    private final FirstNameRepository firstNameRepository;
    private final LastNameRepository lastNameRepository;
    private final RaceRepository raceRepository;
    private final ProfessionRepository professionRepository;
    private final SocialStatusRepository socialStatusRepository;
    private final RaceLanguageRepository raceLanguageRepository;
    private final LanguageRepository languageRepository;

    private List<FirstName> firstNames;
    private List<LastName> lastNames;
    private List<Race> races;
    private List<Profession> professions;
    private List<SocialStatus> socialStatuses;
    private List<RaceLanguage> raceLanguages;
    private List<Language> languages;

    @Autowired
    public NpcAttributeService(
            FirstNameRepository firstNameRepository,
            LastNameRepository lastNameRepository,
            RaceRepository raceRepository,
            ProfessionRepository professionRepository,
            SocialStatusRepository socialStatusRepository,
            RaceLanguageRepository raceLanguageRepository,
            LanguageRepository languageRepository
    ) {
        this.firstNameRepository = firstNameRepository;
        this.lastNameRepository = lastNameRepository;
        this.raceRepository = raceRepository;
        this.professionRepository = professionRepository;
        this.socialStatusRepository = socialStatusRepository;
        this.raceLanguageRepository = raceLanguageRepository;
        this.languageRepository = languageRepository;
    }

    public synchronized void loadData() {
        if (attributes.isEmpty()) {
            attributes.put("firstNames", firstNameRepository.findAll());
            attributes.put("lastNames", lastNameRepository.findAll());
            attributes.put("races", raceRepository.findAll());
            attributes.put("professions", professionRepository.findAll());
            attributes.put("socialStatuses", socialStatusRepository.findAll());
            attributes.put("raceLanguages", raceLanguageRepository.findAll());
            attributes.put("languages", languageRepository.findAll());

            attributes.forEach((key, value) -> ValidationUtility.validateNotEmpty(key, (List<?>) value));
        }
    }


    @SuppressWarnings("unchecked")
    private <T> T getRandomAttribute(String key) {
        loadData();
        return RandomizationUtility.getRandomElement((List<T>) attributes.get(key));
    }

    public FirstName getRandomFirstName() {
        return getRandomAttribute("firstNames");
    }

    public LastName getRandomLastName() {
        return getRandomAttribute("lastNames");
    }

    public Race getRandomRace() {
        return getRandomAttribute("races");
    }

    public Profession getRandomProfession() {
        return getRandomAttribute("professions");
    }

    public SocialStatus getRandomSocialStatus() {
        return getRandomAttribute("socialStatuses");
    }

    public Language getRandomLanguage() {
        return getRandomAttribute("languages");
    }

    public int generateRandomAge(Race race) {
        int[] range = new int[]{18, 75};
        switch (race.getRaceName().toLowerCase()) {
            case "elf" -> range = new int[]{30, 700};
            case "dwarf" -> range = new int[]{20, 300};
            case "halfling" -> range = new int[]{15, 55};
            case "dragonborn" -> range = new int[]{16, 67};
            case "gnome" -> range = new int[]{14, 46};
            case "tiefling" -> range = new int[]{21, 175};
            case "half-elf" -> range = new int[]{18, 120};
            case "human" -> range = new int[]{18, 75};
        }
        return RandomizationUtility.getRandomInt(range[0], range[1]);
    }

    public Gender getRandomGender() {
        return RandomizationUtility.getRandomGender();
    }

}

