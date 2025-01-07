package com.boerma.npcgenerator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "race_name")
    private String raceName;

    public Race() {
    }

    public Race(int id, String raceName) {
        this.id = id;
        this.raceName = raceName;
    }

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", raceName='" + raceName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getRaceName() {
        return raceName;
    }
}
