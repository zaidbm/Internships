package com.internship.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.app.entities.Intern;

public interface InternRepository extends JpaRepository<Intern,Long> {
	public Page<Intern> findByNameContainsOrderByNameAsc(String keyword,Pageable page);
	public Long Count();
}