package com.gestion.app.entities;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class InternshipType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Type name is Required")
    private String name;
    
    @OneToMany(mappedBy = "internship")
    private List<Internship> internships;

    public InternshipType() {}

    public InternshipType(String name) {
        this.name = name;
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

	public List<Internship> getInternships() {
		return internships;
	}

	public void setInternships(List<Internship> internships) {
		this.internships = internships;
	}
    
    
    
}


