package com.boerma.npcgenerator.service;

import com.boerma.npcgenerator.domain.Npc;
import com.boerma.npcgenerator.domain.Profession;
import com.boerma.npcgenerator.domain.SocialStatus;
import com.boerma.npcgenerator.repository.NpcRepository;
import com.boerma.npcgenerator.repository.ProfessionRepository;
import com.boerma.npcgenerator.repository.SocialStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NpcModificationService {

    @Autowired
    private NpcRepository npcRepository;

    @Autowired
    private SocialStatusRepository socialStatusRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    public Npc getNpcById(int npcId) {
        return npcRepository.findById(npcId).orElse(null);
    }

    public List<Profession> getProfessionsBySocialStatus(int socialStatusId) {
        return professionRepository.findAll().stream()
                .filter(profession -> profession.getSocialStatusId() == socialStatusId)
                .collect(Collectors.toList());
    }

    public List<SocialStatus> getAllSocialStatusesOrdered() {
        return socialStatusRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public List<Profession> getFilteredProfessions(int socialStatusId) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public List<SocialStatus> getAllSocialStatuses() {
        return socialStatusRepository.findAll();
    }

    public List<Profession> getAllProfessions() {
        return professionRepository.findAll();
    }

    public String updateNpcAttributes(int npcId, Integer newSocialStatusId, Integer newProfessionId) {
        Npc npc = npcRepository.findById(npcId).orElseThrow(() ->
                new IllegalArgumentException("NPC with ID " + npcId + " does not exist. Please enter a valid ID."));

        if (newSocialStatusId != null) {
            SocialStatus socialStatus = socialStatusRepository.findById(newSocialStatusId).orElseThrow(() ->
                    new IllegalArgumentException("Social Status with ID " + newSocialStatusId + " does not exist. Available options are ..."));
            npc.setSocialStatusId(newSocialStatusId);
        }

        if (newProfessionId != null) {
            Profession profession = professionRepository.findById(newProfessionId).orElseThrow(() ->
                    new IllegalArgumentException("Profession with ID " + newProfessionId + " does not exist. Please choose from ..."));

            if (!profession.getSocialStatusId().equals(newSocialStatusId)) {
                throw new IllegalArgumentException("Profession does not align with the provided Social Status. Ensure the profession is valid for the selected social status.");
            }
            npc.setProfessionId(newProfessionId);
        }

        npcRepository.save(npc);
        return "NPC updated successfully!";
    }
}

