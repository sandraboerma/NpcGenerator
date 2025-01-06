package com.boerma.npcgenerator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "language_name")
    private String languageName;

    public Language() {
    }

    public Language(int id, String languageName) {
        this.id = id;
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return "LastName{" +
                "id=" + id +
                ", lastName='" + languageName + '\'' +
                '}';
    }
}
