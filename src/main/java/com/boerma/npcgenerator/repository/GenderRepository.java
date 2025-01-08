package com.boerma.npcgenerator.repository;

import com.boerma.npcgenerator.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {

}