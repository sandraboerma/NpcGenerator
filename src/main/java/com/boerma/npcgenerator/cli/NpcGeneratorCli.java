package com.boerma.npcgenerator.cli;

import com.boerma.npcgenerator.domain.Npc;
import com.boerma.npcgenerator.domain.Profession;
import com.boerma.npcgenerator.dto.NpcDisplayDto;
import com.boerma.npcgenerator.repository.JdbcNpcRepository;
import com.boerma.npcgenerator.repository.NpcRepository;
import com.boerma.npcgenerator.service.NpcCreationService;
import com.boerma.npcgenerator.service.NpcModificationService;
import com.boerma.npcgenerator.utility.InputUtility;
import com.boerma.npcgenerator.utility.NpcDisplayUtility;
import com.boerma.npcgenerator.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NpcGeneratorCli {
    private final NpcModificationService npcModificationService;
    private final JdbcNpcRepository jdbcNpcRepository;

    @Autowired
    public NpcGeneratorCli(NpcModificationService npcModificationService, JdbcNpcRepository jdbcNpcRepository) {
        this.npcModificationService = npcModificationService;
        this.jdbcNpcRepository = jdbcNpcRepository;
    }


    public void start() {
        System.out.println("Welcome to the NPC Generator!");
        while (true) {
            try {
                System.out.println("\nOptions:");
                System.out.println("1. View All NPCs");
                System.out.println("2. Modify NPC Attributes");
                System.out.println("3. Delete NPC");
                System.out.println("4. Exit");
                int choice = InputUtility.getInt("Enter your choice: ");

                switch (choice) {
                    case 1 -> handleViewAllNpcs();
                    case 2 -> handleModifyNpcAttributes();
                    case 3 -> handleDeleteNpc();
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

    private void handleViewAllNpcs() {
        try {
            List<Npc> allNpcs = jdbcNpcRepository.findAll();
            if (allNpcs.isEmpty()) {
                System.out.println("No NPCs found.");
                return;
            }
            NpcDisplayUtility npcDisplayUtility = new NpcDisplayUtility(jdbcNpcRepository);
            npcDisplayUtility.displayNpcTableJdbc(allNpcs, jdbcNpcRepository);
        } catch (Exception e) {
            System.out.println("Error fetching NPCs: " + e.getMessage());
        }
    }



    private void handleModifyNpcAttributes() {
        try {
            handleViewAllNpcs();
            int npcId = InputUtility.getInt("Enter the NPC ID to modify: ");
            Npc npc = jdbcNpcRepository.findById(npcId);
            if (npc == null) {
                System.out.println("Invalid input: NPC with ID " + npcId + " does not exist.");
                return;
            }

            System.out.println("Options for modification:");
            System.out.println("1. Update Age");
            System.out.println("2. Update Social Status");
            System.out.println("3. Update Profession");
            System.out.println("4. Update All");
            int modificationChoice = InputUtility.getInt("Enter your choice: ");

            switch (modificationChoice) {
                case 1 -> {
                    int newAge = InputUtility.getInt("Enter new age: ");
                    npc.setAge(newAge);
                }
                case 2 -> {
                    int newSocialStatusId = InputUtility.getInt("Enter new Social Status ID: ");
                    npcModificationService.updateSocialStatus(npcId, newSocialStatusId);
                    System.out.println("Social Status updated successfully!");
                }
                case 3 -> {
                    int newProfessionId = InputUtility.getInt("Enter new Profession ID: ");
                    npcModificationService.updateProfession(npcId, newProfessionId, npc.getSocialStatusId());
                    System.out.println("Profession updated successfully!");
                }
                case 4 -> {
                    int newAge = InputUtility.getInt("Enter new age: ");
                    int newSocialStatusId = InputUtility.getInt("Enter new Social Status ID: ");
                    npcModificationService.updateSocialStatus(npcId, newSocialStatusId);

                    int newProfessionId = InputUtility.getInt("Enter new Profession ID: ");
                    npcModificationService.updateProfession(npcId, newProfessionId, newSocialStatusId);

                    npc.setAge(newAge);
                    jdbcNpcRepository.save(npc);
                    System.out.println("NPC updated successfully!");
                }
            }

            jdbcNpcRepository.save(npc);
            System.out.println("NPC updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error during NPC modification: " + e.getMessage());
        }
    }


    private void handleDeleteNpc() {
        try {
            handleViewAllNpcs();
            int npcId = InputUtility.getInt("Enter the NPC ID to delete: ");
            jdbcNpcRepository.deleteById(npcId);
            System.out.println("NPC with ID " + npcId + " has been successfully deleted.");
        } catch (Exception e) {
            System.out.println("Error during deletion: " + e.getMessage());
        }
    }

}
