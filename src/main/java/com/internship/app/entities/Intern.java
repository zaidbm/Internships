package com.internship.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Intern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is Required")
    @Size(max = 30, message = "Name cannot exceed 30 characters")
    private String name;

    @NotBlank(message = "Telephone number is Required")
    @Size(max = 20, message = "Telephone number must be at most 20 characters")
    private String tel;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;

    @Size(max = 50, message = "Picture cannot exceed 50 characters")
    private String picture;
    
    @Size(max = 30, message = "Portfolio URL cannot exceed 30 characters")
    private String portfolio;

    @NotNull(message = "City is Required")
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String availability;

    @Size(max = 1500, message = "Skills cannot exceed 1500 characters")
    private String skills;

    //@Size(max = 50, message = "Resume URL cannot exceed 50 characters")
    private String resume;

    public Intern() {}

    public Intern(String name, String tel, String email, String picture, String portfolio, City city, String availability, String skills, String resume) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.picture = picture;
        this.portfolio = portfolio;
        this.city = city;
        this.availability = availability;
        this.skills = skills;
        this.resume = resume;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}

