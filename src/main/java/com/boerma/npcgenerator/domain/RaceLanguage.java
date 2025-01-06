package com.boerma.npcgenerator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "race_languages")
public class RaceLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "race_id")
    private int raceId;

    @Column(name = "language_id")
    private int languageId;

    public RaceLanguage() {
    }

    public RaceLanguage(int raceId, int languageId) {
        this.raceId = raceId;
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "RaceLanguage {" +
                "raceId=" + raceId +
                ", languageId=" + languageId +
                '}';
    }
}
