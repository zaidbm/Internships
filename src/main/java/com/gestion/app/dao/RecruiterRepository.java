package com.gestion.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.app.entities.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
	public Page<Recruiter> findByNameContainsOrderByNameAsc(String keyword,Pageable page);
	public Long Count();
}
