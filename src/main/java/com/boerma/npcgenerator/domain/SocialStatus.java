package com.boerma.npcgenerator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "social_statuses")
public class SocialStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status_name")
    private String statusName;

    public SocialStatus() {
    }

    public SocialStatus(int id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "SocialStatus{" +
                "id=" + id +
                ", socialStatus='" + statusName + '\'' +
                '}';
    }
}
