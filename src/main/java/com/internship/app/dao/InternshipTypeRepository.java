package com.internship.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.app.entities.InternshipType;

public interface InternshipTypeRepository extends JpaRepository<InternshipType, Long >{
	public Page<InternshipType> findByTitleContainsOrderByTitleAsc(String keyword,Pageable page);

}
