package com.boerma.npcgenerator.cli;

import com.boerma.npcgenerator.service.NpcCreationService;
import com.boerma.npcgenerator.utility.InputUtility;
import com.boerma.npcgenerator.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NpcGeneratorCli {
    private final NpcCreationService npcCreationService;

    @Autowired
    public NpcGeneratorCli(NpcCreationService npcCreationService) {
        this.npcCreationService = npcCreationService;
    }

    public void start() {
        System.out.println("Welcome to the NPC Generator!");
        while (true) {
            try {
                System.out.println("\nOptions:");
                System.out.println("1. Generate NPCs");
                System.out.println("2. Exit");
                int choice = InputUtility.getInt("Enter your choice: ");

                if (choice == 2) {
                    System.out.println("Exiting... Goodbye!");
                    break;
                } else if (choice == 1) {
                    int count = InputUtility.getInt("Enter the number of NPCs to generate (1–10): ");
                    ValidationUtility.validateIntRange("NPC Count", count, 1, 10);
                    npcCreationService.generateAndDisplayNpcs(count);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        InputUtility.closeScanner();
    }
}
