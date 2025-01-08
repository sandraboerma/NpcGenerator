package com.boerma.npcgenerator.repository;

import com.boerma.npcgenerator.domain.Npc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NpcRepository extends JpaRepository<Npc, Integer> {

    @Query(value = "SELECT n.id, fn.first_name, ln.last_name, n.age, r.race_name, p.profession_name, ss.status_name, g.gender_name, " +
            "GROUP_CONCAT(l.language_name SEPARATOR ', ') AS languages " +
            "FROM npcs n " +
            "JOIN first_names fn ON n.first_name_id = fn.id " +
            "JOIN last_names ln ON n.last_name_id = ln.id " +
            "JOIN races r ON n.race_id = r.id " +
            "JOIN professions p ON n.profession_id = p.id " +
            "JOIN social_statuses ss ON n.social_status_id = ss.id " +
            "JOIN genders g ON n.gender_id = g.id " +
            "LEFT JOIN npc_languages nl ON n.id = nl.npc_id " +
            "LEFT JOIN languages l ON nl.language_id = l.id " +
            "GROUP BY n.id", nativeQuery = true)
    List<Object[]> findAllNpcDetails();

    @Query(value = "SELECT n.id, fn.first_name, ln.last_name, n.age, r.race_name, p.profession_name, ss.status_name, g.gender_name, " +
            "GROUP_CONCAT(l.language_name SEPARATOR ', ') AS languages " +
            "FROM npcs n " +
            "JOIN first_names fn ON n.first_name_id = fn.id " +
            "JOIN last_names ln ON n.last_name_id = ln.id " +
            "JOIN races r ON n.race_id = r.id " +
            "JOIN professions p ON n.profession_id = p.id " +
            "JOIN social_statuses ss ON n.social_status_id = ss.id " +
            "JOIN genders g ON n.gender_id = g.id " +
            "LEFT JOIN npc_languages nl ON n.id = nl.npc_id " +
            "LEFT JOIN languages l ON nl.language_id = l.id " +
            "WHERE n.id IN :ids " +
            "GROUP BY n.id",
            nativeQuery = true)
    List<Object[]> findAllNpcDetails(List<Integer> ids);

}
