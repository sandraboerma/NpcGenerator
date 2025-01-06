package com.boerma.npcgenerator.repository;

import com.boerma.npcgenerator.domain.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {

}
