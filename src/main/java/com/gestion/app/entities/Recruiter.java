package com.gestion.app.entities;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is Required")
    @Size(max = 30, message = "Name cannot exceed 30 characters")
    private String name;

    @NotBlank(message = "Telephone number is required")
    @Size(max = 20, message = "Telephone number cannot exceed 20 characters")
    private String tel;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid email format")
    @Size(max = 30, message = "Email cannot exceed 30 characters")
    private String email;

    @NotBlank(message = "Company name is Required")
    @Size(max = 255, message = "Company name must be at most 255 characters")
    private String companyName;

    
    private String companyDescription;
    
    private String companyLogo;
    
    private String companyWebsite;

    @NotNull(message = "City must be specified")
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @NotBlank(message = "Requirements is Required")
    private String requirements;
    
    @OneToMany(mappedBy = "recruiter")
    List<Internship> internships;
    
    public Recruiter() {
    }

    public Recruiter(String name, String tel, String email, String companyName, City city, String requirements,String companyDescription,String companyWebsite,String companyLogo) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.companyName = companyName;
        this.companyDescription=companyDescription;
        this.companyWebsite=companyWebsite;
        this.city = city;
        this.companyLogo=companyLogo;
        this.requirements = requirements;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

	public List<Internship> getInternships() {
		return internships;
	}

	public void setInternships(List<Internship> internships) {
		this.internships = internships;
	}
    //aaa
    
}
