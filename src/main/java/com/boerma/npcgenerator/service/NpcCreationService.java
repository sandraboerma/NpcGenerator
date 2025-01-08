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

    @Autowired
    public NpcCreationService(NpcRepository npcRepository, NpcAttributeService npcAttributeService) {
        this.npcRepository = npcRepository;
        this.npcAttributeService = npcAttributeService;
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
                gender,
                age
        );
        Npc savedNpc = npcRepository.save(npc);
        System.out.println("Saved NPC ID: " + savedNpc.getId());
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
        return npcRepository.findAllNpcDetails(newNpcIds);
    }

    public List<NpcDisplayDto> viewAllNpcs() {
        return npcRepository.findAllNpcDetails();
    }

}
