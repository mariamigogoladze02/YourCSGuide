package com.task.yourcsguide.entity.dto;

import java.util.List;

public class ProfileDTO {
    private Long id;
    private String name;
    private String surname;
    private String description;
    private String experience;
    private List<String> skills;
    private String email;

    public ProfileDTO() {
    }

    public ProfileDTO(Long id, String name, String surname, String description, String experience, List<String> skills, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.experience = experience;
        this.skills = skills;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
