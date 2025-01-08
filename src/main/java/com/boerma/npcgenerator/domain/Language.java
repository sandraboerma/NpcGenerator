package com.boerma.npcgenerator.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "language_name")
    private String languageName;

    @ManyToMany(mappedBy = "languages")
    private Set<Npc> npcs = new HashSet<>();

    public Language() {
    }

    public Language(int id, String languageName, Set<Npc> npcs) {
        this.id = id;
        this.languageName = languageName;
        this.npcs = npcs != null ? npcs : new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Set<Npc> getNpcs() {
        return npcs;
    }

    public void setNpcs(Set<Npc> npcs) {
        this.npcs = npcs;
    }

    @Override
    public String toString() {
        return "Language {" +
                "id=" + id +
                ", languageName='" + languageName + '\'' +
                ", npcCount=" + (npcs != null ? npcs.size() : 0) +
                '}';
    }
}
