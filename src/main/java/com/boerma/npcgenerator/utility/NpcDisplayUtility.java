package com.boerma.npcgenerator.utility;

import com.boerma.npcgenerator.domain.Npc;
import com.boerma.npcgenerator.dto.NpcDisplayDto;
import com.boerma.npcgenerator.repository.JdbcNpcRepository;

import java.util.List;

public class NpcDisplayUtility {

    private final JdbcNpcRepository jdbcNpcRepository;

    public NpcDisplayUtility(JdbcNpcRepository jdbcNpcRepository) {
        this.jdbcNpcRepository = jdbcNpcRepository;
    }

    public static void displayNpc(NpcDisplayDto npcDto) {
        System.out.println("----------------------------------");
        System.out.println("ID: " + npcDto.getId());
        System.out.println("Name: " + npcDto.getFirstName() + " " + npcDto.getLastName());
        System.out.println("Age: " + npcDto.getAge());
        System.out.println("Race: " + npcDto.getRace());
        System.out.println("Profession: " + npcDto.getProfession());
        System.out.println("Social Status: " + npcDto.getSocialStatus());
        System.out.println("Gender: " + npcDto.getGender());
        System.out.println("Languages: " + String.join(", ", npcDto.getLanguages()));
        System.out.println("----------------------------------");
    }


    public static void displayNpcs(List<NpcDisplayDto> npcDtos) {
        System.out.println("Generated NPCs:");
        for (NpcDisplayDto npcDto : npcDtos) {
            displayNpc(npcDto);
        }
    }

    public static void displayNpcTable(List<NpcDisplayDto> npcDetails) {
        System.out.println("+----+---------------+---------------+-----+--------------+-----------------------+---------------+--------+-----------------------------+");
        System.out.println("| ID | First Name    | Last Name     | Age | Race         | Profession            | Social Stat   | Gender | Languages                   |");
        System.out.println("+----+---------------+---------------+-----+--------------+-----------------------+---------------+--------+-----------------------------+");

        for (NpcDisplayDto npc : npcDetails) {
            System.out.printf(
                    "| %2d | %-13s | %-13s | %3d | %-12s | %-21s | %-13s | %-6s | %-27s |\n",
                    npc.getId(),
                    npc.getFirstName(),
                    npc.getLastName(),
                    npc.getAge(),
                    npc.getRace(),
                    npc.getProfession(),
                    npc.getSocialStatus(),
                    npc.getGender(),
                    String.join(", ", npc.getLanguages())
            );
        }

        System.out.println("+----+---------------+---------------+-----+--------------+-----------------------+---------------+--------+-----------------------------+");
    }

    public static void displayNpcTableJdbc(List<Npc> npcList, JdbcNpcRepository jdbcNpcRepository) {
        System.out.println("+----+---------------+---------------+-----+--------------+-----------------------+---------------+--------+-----------------------------+");
        System.out.println("| ID | First Name    | Last Name     | Age | Race         | Profession            | Social Stat   | Gender | Languages                   |");
        System.out.println("+----+---------------+---------------+-----+--------------+-----------------------+---------------+--------+-----------------------------+");

        for (Npc npc : npcList) {
            String firstName = jdbcNpcRepository.findFirstNameById(npc.getFirstNameId());
            String lastName = jdbcNpcRepository.findLastNameById(npc.getLastNameId());
            String race = jdbcNpcRepository.findRaceById(npc.getRaceId());
            String profession = jdbcNpcRepository.findProfessionById(npc.getProfessionId());
            String socialStatus = jdbcNpcRepository.findSocialStatusById(npc.getSocialStatusId());
            String gender = jdbcNpcRepository.findGenderById(npc.getGenderId());
            List<String> languages = jdbcNpcRepository.findLanguagesByNpcId(npc.getId());
            String languageStr = String.join(", ", languages);

            System.out.printf(
                    "| %2d | %-13s | %-13s | %3d | %-12s | %-21s | %-13s | %-6s | %-27s |\n",
                    npc.getId(),
                    firstName,
                    lastName,
                    npc.getAge(),
                    race,
                    profession,
                    socialStatus,
                    gender,
                    languageStr
            );
        }

        System.out.println("+----+---------------+---------------+-----+--------------+-----------------------+---------------+--------+-----------------------------+");
    }

}
