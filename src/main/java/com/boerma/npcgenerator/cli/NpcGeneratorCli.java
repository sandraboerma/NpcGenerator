package com.boerma.npcgenerator.cli;

import com.boerma.npcgenerator.domain.Npc;
import com.boerma.npcgenerator.domain.Profession;
import com.boerma.npcgenerator.dto.NpcDisplayDto;
import com.boerma.npcgenerator.repository.NpcRepository;
import com.boerma.npcgenerator.service.NpcCreationService;
import com.boerma.npcgenerator.service.NpcModificationService;
import com.boerma.npcgenerator.utility.InputUtility;
import com.boerma.npcgenerator.utility.NpcDisplayUtility;
import com.boerma.npcgenerator.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NpcGeneratorCli {
    private final NpcRepository npcRepository;
    private final NpcCreationService npcCreationService;
    private final NpcModificationService npcModificationService;

    @Autowired
    public NpcGeneratorCli(NpcRepository npcRepository,
                           NpcCreationService npcCreationService,
                           NpcModificationService npcModificationService) {
        this.npcRepository = npcRepository;
        this.npcCreationService = npcCreationService;
        this.npcModificationService = npcModificationService;
    }

    public void start() {
        System.out.println("Welcome to the NPC Generator!");
        while (true) {
            try {
                System.out.println("\nOptions:");
                System.out.println("1. Generate NPCs");
                System.out.println("2. View All NPCs");
                System.out.println("3. Modify NPC Attributes");
                System.out.println("4. Exit");
                int choice = InputUtility.getInt("Enter your choice: ");

                switch (choice) {
                    case 1 -> handleGenerateNpcs();
                    case 2 -> handleViewAllNpcs();
                    case 3 -> handleModifyNpcAttributes();
                    case 4 -> {
                        System.out.println("Exiting... Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void handleGenerateNpcs() {
        try {
            int count = InputUtility.getInt("Enter the number of NPCs to generate (1–10): ");
            ValidationUtility.validateIntRange("NPC Count", count, 1, 10);

            List<NpcDisplayDto> newNpcs = npcCreationService.createAndSaveNpcs(count);
            NpcDisplayUtility.displayNpcs(newNpcs);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error during NPC generation: " + e.getMessage());
        }
    }

    private void handleViewAllNpcs() {
        try {
            List<NpcDisplayDto> allNpcs = npcCreationService.viewAllNpcs();
            if (allNpcs.isEmpty()) {
                System.out.println("No NPCs found.");
            } else {
                NpcDisplayUtility.displayNpcTable(allNpcs);
            }
        } catch (Exception e) {
            System.out.println("Error fetching NPCs: " + e.getMessage());
        }
    }

    private void handleModifyNpcAttributes() {
        try {

            System.out.println("Here are the available NPCs:");
            List<NpcDisplayDto> allNpcs = npcCreationService.viewAllNpcs();
            if (allNpcs.isEmpty()) {
                System.out.println("No NPCs available for modification.");
                return;
            }
            NpcDisplayUtility.displayNpcTable(allNpcs);

            int npcId = InputUtility.getInt("Enter the NPC ID to modify: ");

            Npc npc = npcModificationService.getNpcById(npcId);
            if (npc == null) {
                System.out.println("Invalid input: NPC with ID " + npcId + " does not exist. Please enter a valid ID.");
                return;
            }

            System.out.println("Options for modification:");
            System.out.println("1. Update Social Status");
            System.out.println("2. Update Profession");
            System.out.println("3. Update Both");
            int modificationChoice = InputUtility.getInt("Enter your choice: ");
            Integer newSocialStatusId = null;
            Integer newProfessionId = null;

            if (modificationChoice == 1 || modificationChoice == 3) {
                System.out.println("Available Social Status IDs:");
                npcModificationService.getAllSocialStatusesOrdered().forEach(status ->
                        System.out.println(status.getId() + ": " + status.getStatusName()));
                newSocialStatusId = InputUtility.getInt("Enter new Social Status ID: ");
            }

            if (modificationChoice == 2 || modificationChoice == 3) {
                System.out.println("Available Profession IDs:");

                // Fetch valid professions using a unified method
                List<Profession> validProfessions = npcModificationService.getFilteredProfessions(npc.getSocialStatusId());
                if (validProfessions.isEmpty()) {
                    System.out.println("No valid professions available for the selected Social Status.");
                    return;
                }

                // Display valid professions
                validProfessions.forEach(profession ->
                        System.out.println(profession.getId() + ": " + profession.getProfessionName()));

                List<Integer> validProfessionIds = validProfessions.stream()
                        .map(Profession::getId)
                        .toList();

                // Prompt user for new Profession ID
                newProfessionId = InputUtility.getInt("Enter new Profession ID: ");

                // Validate user input
                if (!validProfessionIds.contains(newProfessionId)) {
                    System.out.println("Invalid input: The selected Profession ID is not valid for the current Social Status.");
                    return;
                }
            }

            String result = npcModificationService.updateNpcAttributes(npcId, newSocialStatusId, newProfessionId);
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error during NPC modification: " + e.getMessage());
        }
    }






}
