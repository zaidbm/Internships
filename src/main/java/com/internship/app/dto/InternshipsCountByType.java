package com.internship.app.dto;

public class InternshipsCountByType {
	private Long internshipCount;
	 private String typeName;
	 
	public InternshipsCountByType() {}

	public InternshipsCountByType(Long internshipCount, String typeName) {

		this.internshipCount = internshipCount;
		this.typeName = typeName;
	}

	public Long getInternshipCount() {
		return internshipCount;
	}

	public void setInternshipCount(Long internshipCount) {
		this.internshipCount = internshipCount;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	 
	 
}
