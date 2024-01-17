package com.gestion.app.entities;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    @NotBlank(message = "Title is Required")
    @Size(max = 20, message = "Title cannot exceed 20 characters")
    private String title;

    @Lob
    @NotNull(message = "description is Required")
    private String description;

    @NotNull(message = "Publication date is Required")
    private LocalDateTime publicationDate;
    
    

    @NotNull(message = "Application deadline is Required")
    private LocalDateTime applicationDeadline;
    

    @NotNull(message = "Internship type must be specified")
    @ManyToOne
    @JoinColumn(name = "type_id")
    private InternshipType internshipType;

    public Internship() {}

    public Internship(Recruiter recruiter, String title, String description, LocalDateTime publicationDate, LocalDateTime applicationDeadline, InternshipType internshipType) {
        this.recruiter = recruiter;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.applicationDeadline = applicationDeadline;
        this.internshipType = internshipType;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public InternshipType getInternshipType() {
        return internshipType;
    }

    public void setInternshipType(InternshipType internshipType) {
        this.internshipType = internshipType;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}

	public LocalDateTime getApplicationDeadline() {
		return applicationDeadline;
	}

	public void setApplicationDeadline(LocalDateTime applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}
    
}



