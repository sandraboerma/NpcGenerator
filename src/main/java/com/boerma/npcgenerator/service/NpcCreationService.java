package com.boerma.npcgenerator.service;

import com.boerma.npcgenerator.domain.*;
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

    @Autowired
    public NpcCreationService(NpcRepository npcRepository, NpcAttributeService npcAttributeService) {
        this.npcRepository = npcRepository;
        this.npcAttributeService = npcAttributeService;
    }

    public Npc generateAndSaveNpc() {
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
                gender,
                age
        );
        return npcRepository.save(npc);
    }

    public List<Npc> generateAndSaveNpcs(int count) {
        ValidationUtility.validateIntRange("NPC count: ", count, 1, 10);

        npcAttributeService.loadData();

        List<Npc> npcs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            npcs.add(generateAndSaveNpc());
        }

        return npcs;
    }

    public void generateAndDisplayNpcs(int count) {
        List<Npc> generatedNpcs = generateAndSaveNpcs(count);
        System.out.println("Generated NPCs:");
        generatedNpcs.forEach(System.out::println);
    }
}
