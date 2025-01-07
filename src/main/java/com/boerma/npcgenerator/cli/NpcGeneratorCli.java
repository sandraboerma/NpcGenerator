package com.boerma.npcgenerator.cli;

import com.boerma.npcgenerator.dto.NpcDisplayDto;
import com.boerma.npcgenerator.repository.NpcRepository;
import com.boerma.npcgenerator.service.NpcCreationService;
import com.boerma.npcgenerator.utility.InputUtility;
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

                if (choice == 3) {
                    System.out.println("Exiting... Goodbye!");
                    break;
                } else if (choice == 1) {
                    int count = InputUtility.getInt("Enter the number of NPCs to generate (1–10): ");
                    ValidationUtility.validateIntRange("NPC Count", count, 1, 10);
                    npcCreationService.generateAndDisplayNpcs(count);
                } else if (choice == 2) {
                    displayAllNpcs();
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        InputUtility.closeScanner();
    }

    public void displayAllNpcs() {
        List<NpcDisplayDto> npcDetails = npcRepository.findAllNpcDetails();

        System.out.println("+----+---------------+---------------+-----+--------------+-----------------------+---------------+--------+");
        System.out.println("| ID | First Name    | Last Name     | Age | Race         | Profession            | Social Stat   | Gender |");
        System.out.println("+----+---------------+---------------+-----+--------------+-----------------------+---------------+--------+");

        for (NpcDisplayDto npc : npcDetails) {
            System.out.printf(
                    "| %2d | %-13s | %-13s | %3d | %-12s | %-21s | %-13s | %-6s |\n",
                    npc.getId(),
                    npc.getFirstName(),
                    npc.getLastName(),
                    npc.getAge(),
                    npc.getRace(),
                    npc.getProfession(),
                    npc.getSocialStatus(),
                    npc.getGender()
            );
        }

        System.out.println("+----+---------------+---------------+-----+--------------+-----------------------+---------------+--------+");

    }

}
