package com.internship.app.dto;

public class InternshipCountPerRecruiter {
	 private Long internshipCount;
	 private String recruiterName;
	 
	 public InternshipCountPerRecruiter() {	}

	public InternshipCountPerRecruiter(Long internshipCount, String recruiterName) {
		this.internshipCount = internshipCount;
		this.recruiterName = recruiterName;
	}

	public Long getInternshipCount() {
		return internshipCount;
	}

	public void setInternshipCount(Long internshipCount) {
		this.internshipCount = internshipCount;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	 
	 
	 
	 
}
