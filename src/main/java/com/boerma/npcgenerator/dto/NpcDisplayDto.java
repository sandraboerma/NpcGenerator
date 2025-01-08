package com.boerma.npcgenerator.dto;

import com.boerma.npcgenerator.domain.Gender;

public class NpcDisplayDto {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String race;
    private String profession;
    private String socialStatus;
    private String gender;

    public NpcDisplayDto(int id, String firstName, String lastName, int age, String race,
                         String profession, String socialStatus, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.race = race;
        this.profession = profession;
        this.socialStatus = socialStatus;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getRace() {
        return race;
    }

    public String getProfession() {
        return profession;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "NpcDisplayDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", race='" + race + '\'' +
                ", profession='" + profession + '\'' +
                ", socialStatus='" + socialStatus + '\'' +
                ", gender=" + gender +
                '}';
    }
}
