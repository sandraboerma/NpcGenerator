package com.boerma.npcgenerator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "professions")
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "profession_name")
    private String professionName;

    @Column(name = "social_status_id")
    private int socialStatusId;

    public Profession() {
    }

    public Profession(int id, String professionName, int socialStatusId) {
        this.id = id;
        this.professionName = professionName;
        this.socialStatusId = socialStatusId;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "id=" + id +
                ", professionName='" + professionName + '\'' +
                ", socialStatusId=" + socialStatusId +
                '}';
    }
}
