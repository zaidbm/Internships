package com.internship.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.app.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {
	public Page<City> findByNameContainsOrderByNameAsc(String keyword,Pageable page);
}

