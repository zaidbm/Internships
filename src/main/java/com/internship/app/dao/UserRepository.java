package com.internship.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internship.app.entities.*;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUsername(String username);
	public Long Count();
	
	
}