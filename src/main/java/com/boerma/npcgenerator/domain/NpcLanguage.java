package com.boerma.npcgenerator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "npc_languages")
public class NpcLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "npc_id")
    private int npcId;

    @Column(name = "language_id")
    private int languageId;

    public NpcLanguage() {
    }

    public NpcLanguage(int npcId, int languageId) {
        this.npcId = npcId;
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "NpcLanguage{" +
                "npcId=" + npcId +
                ", languageId=" + languageId +
                '}';
    }

}
