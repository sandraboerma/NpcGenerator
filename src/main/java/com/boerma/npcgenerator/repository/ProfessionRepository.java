package com.boerma.npcgenerator.repository;

import com.boerma.npcgenerator.domain.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Integer> {

    @Query("SELECT p FROM Profession p WHERE p.socialStatusId = :socialStatusId ORDER BY p.id ASC")
    List<Profession> findBySocialStatusId(@Param("socialStatusId") int socialStatusId);

}
