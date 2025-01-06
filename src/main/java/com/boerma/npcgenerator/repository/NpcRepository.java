package com.boerma.npcgenerator.repository;

import com.boerma.npcgenerator.domain.Npc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NpcRepository extends JpaRepository<Npc, Integer> {

}
