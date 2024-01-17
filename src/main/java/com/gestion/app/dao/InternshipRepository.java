package com.gestion.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.app.entities.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
	public Page<Internship> findByTitleContainsOrderByTitleAsc(String keyword,Pageable page);
	public Long Count();
	
}
