package com.boerma.npcgenerator.domain;

public class NpcFacade {

    public static Npc createNpc(
            int firstNameId,
            int lastNameId,
            int raceId,
            int professionId,
            int socialStatusId,
            Gender gender,
            int age
    ) {
        Npc npc = new Npc();
        npc.setFirstNameId(firstNameId);
        npc.setLastNameId(lastNameId);
        npc.setRaceId(raceId);
        npc.setProfessionId(professionId);
        npc.setSocialStatusId(socialStatusId);
        npc.setGender(gender);
        npc.setAge(age);
        return npc;
    }
}

