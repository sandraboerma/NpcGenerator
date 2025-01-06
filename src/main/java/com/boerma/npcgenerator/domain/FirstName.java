package com.boerma.npcgenerator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "first_names")
public class FirstName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    public FirstName() {
    }

    public FirstName(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "FirstName{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
