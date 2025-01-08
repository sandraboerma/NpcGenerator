package com.boerma.npcgenerator.cli;

import com.boerma.npcgenerator.dto.NpcDisplayDto;
import com.boerma.npcgenerator.repository.NpcRepository;
import com.boerma.npcgenerator.service.NpcCreationService;
import com.boerma.npcgenerator.utility.InputUtility;
import com.boerma.npcgenerator.utility.NpcDisplayUtility;
import com.boerma.npcgenerator.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NpcGeneratorCli {
    private final NpcCreationService npcCreationService;
    private final NpcRepository npcRepository;

    @Autowired
    public NpcGeneratorCli(NpcCreationService npcCreationService, NpcRepository npcRepository) {
        this.npcCreationService = npcCreationService;
        this.npcRepository = npcRepository;
    }

    public void start() {
        System.out.println("Welcome to the NPC Generator!");
        while (true) {
            try {
                System.out.println("\nOptions:");
                System.out.println("1. Generate NPCs");
                System.out.println("2. View All NPCs");
                System.out.println("3. Exit");
                int choice = InputUtility.getInt("Enter your choice: ");

                switch (choice) {
                    case 1 -> handleGenerateNpcs();
                    case 2 -> handleViewAllNpcs();
                    case 3 -> {
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

}
