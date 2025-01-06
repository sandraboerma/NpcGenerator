package com.boerma.npcgenerator.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "npcs")
public class Npc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name_id")
    private int firstNameId;

    @Column(name = "last_name_id")
    private int lastNameId;

    @Column(name = "age")
    private int age;

    @Column(name = "race_id")
    private int raceId;

    @Column(name = "social_status_id")
    private int socialStatusId;

    @Column(name = "profession_id")
    private int professionId;

    @Column(name = "gender_id")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    public Npc() {
    }

    public Npc(int id, int firstNameId, int lastNameId, int age, int raceId, int socialStatusId, int professionId, int genderId) {
        this.id = id;
        this.firstNameId = firstNameId;
        this.lastNameId = lastNameId;
        this.age = age;
        this.raceId = raceId;
        this.socialStatusId = socialStatusId;
        this.professionId = professionId;
        this.gender = new GenderConverter().convertToEntityAttribute(genderId);
    }

    @Override
    public String toString() {
        return "Npc {" +
                "id=" + id +
                ", firstNameId=" + firstNameId +
                ", lastNameId=" + lastNameId +
                ", age=" + age +
                ", raceId=" + raceId +
                ", socialStatusId=" + socialStatusId +
                ", professionId=" + professionId +
                ", gender=" + gender +
                '}';
    }
}
