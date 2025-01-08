package com.boerma.npcgenerator.dto;

import java.util.List;

public class NpcDisplayDto {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String race;
    private String profession;
    private String socialStatus;
    private String gender;
    private List<String> languages;

public NpcDisplayDto(int id, String firstName, String lastName, int age, String race,
                     String profession, String socialStatus, String gender, List<String> languages) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.race = race;
    this.profession = profession;
    this.socialStatus = socialStatus;
    this.gender = gender;
    this.languages = languages != null && !languages.isEmpty()
            ? languages
            : List.of();
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

    public List<String> getLanguages() {
        return languages;
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
                ", gender='" + gender + '\'' +
                ", languages=" + languages +
                '}';
    }
}
