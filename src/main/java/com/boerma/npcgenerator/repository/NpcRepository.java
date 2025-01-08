package com.boerma.npcgenerator.repository;

import com.boerma.npcgenerator.domain.Npc;
import com.boerma.npcgenerator.dto.NpcDisplayDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NpcRepository extends JpaRepository<Npc, Integer> {

    @Query("SELECT new com.boerma.npcgenerator.dto.NpcDisplayDto(n.id, fn.firstName, ln.lastName, n.age, r.raceName, p.professionName, ss.statusName, n.gender) " +
            "FROM Npc n " +
            "JOIN FirstName fn ON n.firstNameId = fn.id " +
            "JOIN LastName ln ON n.lastNameId = ln.id " +
            "JOIN Race r ON n.raceId = r.id " +
            "JOIN Profession p ON n.professionId = p.id " +
            "JOIN SocialStatus ss ON n.socialStatusId = ss.id")
    List<NpcDisplayDto> findAllNpcDetails();

    @Query("SELECT new com.boerma.npcgenerator.dto.NpcDisplayDto(n.id, fn.firstName, ln.lastName, n.age, r.raceName, p.professionName, ss.statusName, n.gender) " +
            "FROM Npc n " +
            "JOIN FirstName fn ON n.firstNameId = fn.id " +
            "JOIN LastName ln ON n.lastNameId = ln.id " +
            "JOIN Race r ON n.raceId = r.id " +
            "JOIN Profession p ON n.professionId = p.id " +
            "JOIN SocialStatus ss ON n.socialStatusId = ss.id " +
            "WHERE n.id IN :ids")
    List<NpcDisplayDto> findAllNpcDetails(List<Integer> ids);

}
