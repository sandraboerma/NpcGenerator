package com.boerma.npcgenerator.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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
    private int genderId;

    @ManyToMany
    @JoinTable(
            name = "npc_languages",
            joinColumns = @JoinColumn(name = "npc_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages = new HashSet<>();

    public Npc() {
    }

    public Npc(int id, int firstNameId, int lastNameId, int age, int raceId, int socialStatusId,
               int professionId, int genderId, Set<Language> languages) {
        this.id = id;
        this.firstNameId = firstNameId;
        this.lastNameId = lastNameId;
        this.age = age;
        this.raceId = raceId;
        this.socialStatusId = socialStatusId;
        this.professionId = professionId;
        this.genderId = genderId;
        this.languages = languages != null ? languages : new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstNameId() {
        return firstNameId;
    }

    public void setFirstNameId(int firstNameId) {
        this.firstNameId = firstNameId;
    }

    public int getLastNameId() {
        return lastNameId;
    }

    public void setLastNameId(int lastNameId) {
        this.lastNameId = lastNameId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getSocialStatusId() {
        return socialStatusId;
    }

    public void setSocialStatusId(int socialStatusId) {
        this.socialStatusId = socialStatusId;
    }

    public int getProfessionId() {
        return professionId;
    }

    public void setProfessionId(int professionId) {
        this.professionId = professionId;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGender(int genderId) {
        this.genderId = genderId;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
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
                ", gender=" + genderId +
                ", languages=" + languages.stream()
                .map(Language::getLanguageName)
                .toList() +
                '}';
    }

}
