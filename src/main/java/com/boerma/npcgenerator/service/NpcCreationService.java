package com.boerma.npcgenerator.service;

import com.boerma.npcgenerator.domain.*;
import com.boerma.npcgenerator.dto.NpcDisplayDto;
import com.boerma.npcgenerator.repository.NpcRepository;
import com.boerma.npcgenerator.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NpcCreationService {
    private final NpcRepository npcRepository;
    private final NpcAttributeService npcAttributeService;
    private final LanguageAssignmentService languageAssignmentService;

    @Autowired
    public NpcCreationService(NpcRepository npcRepository,
                              NpcAttributeService npcAttributeService,
                              LanguageAssignmentService languageAssignmentService) {
        this.npcRepository = npcRepository;
        this.npcAttributeService = npcAttributeService;
        this.languageAssignmentService = languageAssignmentService;
    }

    public Npc createAndSaveNpc() {
        FirstName firstName = npcAttributeService.getRandomFirstName();
        LastName lastName = npcAttributeService.getRandomLastName();
        Race race = npcAttributeService.getRandomRace();
        Profession profession = npcAttributeService.getRandomProfession();
        SocialStatus socialStatus = npcAttributeService.getRandomSocialStatus();
        Gender gender = npcAttributeService.getRandomGender();
        int age = npcAttributeService.generateRandomAge(race);

        Npc npc = NpcFacade.createNpc(
                firstName.getId(),
                lastName.getId(),
                race.getId(),
                profession.getId(),
                socialStatus.getId(),
                gender.getId(),
                age
        );
        Npc savedNpc = npcRepository.save(npc);
        languageAssignmentService.assignLanguagesToNpc(savedNpc, race, socialStatus);
        System.out.println("Saved NPC ID: " + savedNpc.getId());
        npcRepository.save(savedNpc);
        return savedNpc;
    }

    public List<NpcDisplayDto> createAndSaveNpcs(int count) {

        ValidationUtility.validateIntRange("NPC count", count, 1, 10);

        if (!npcAttributeService.isDataLoaded()) {
            npcAttributeService.loadData();
        }

        List<Integer> newNpcIds = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Npc npc = createAndSaveNpc();
            newNpcIds.add(npc.getId());
        }

        System.out.println("Generated NPC IDs: " + newNpcIds);
        return mapResultsToDto(npcRepository.findAllNpcDetails(newNpcIds));
    }

    public List<NpcDisplayDto> viewAllNpcs() {
        return mapResultsToDto(npcRepository.findAllNpcDetails());
    }

    private List<NpcDisplayDto> mapResultsToDto(List<Object[]> results) {
        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (Object[] row : results) {
                System.out.println("Raw result: " + java.util.Arrays.toString(row));
            }
        }

        List<NpcDisplayDto> npcDetails = new ArrayList<>();
        for (Object[] row : results) {
            int id = ((Number) row[0]).intValue();
            String firstName = (String) row[1];
            String lastName = (String) row[2];
            int age = ((Number) row[3]).intValue();
            String race = (String) row[4];
            String profession = (String) row[5];
            String socialStatus = (String) row[6];
            String gender = (String) row[7];
            String concatenatedLanguages = (String) row[8];

            // Split and populate languages or set an empty list if null
            List<String> languages = (concatenatedLanguages != null && !concatenatedLanguages.isBlank())
                    ? List.of(concatenatedLanguages.split(",\\s*"))
                    : List.of();

            npcDetails.add(new NpcDisplayDto(id, firstName, lastName, age, race, profession, socialStatus, gender, languages));
        }
        return npcDetails;
    }

}
