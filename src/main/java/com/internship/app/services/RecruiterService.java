package com.internship.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.internship.app.dao.RecruiterRepository;
import com.internship.app.entities.Recruiter;

public class RecruiterService {
	private RecruiterRepository recruiterRepository;

	
	@Autowired
	public RecruiterService(RecruiterRepository recruiterRepository) {
		this.recruiterRepository=recruiterRepository;
	}
	
	public ResponseEntity<Page<Recruiter>> getAllRecruiters(Pageable pageable) {
        Page<Recruiter> recruiter = recruiterRepository.findAll(pageable);
        return ResponseEntity.ok(recruiter);
    }

    public ResponseEntity<Recruiter> getRecruiterById(Long id) {
        return recruiterRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Recruiter> createRecruiter(Recruiter recruiter) {
        Recruiter createdRecruiter = recruiterRepository.save(recruiter);
        return ResponseEntity.ok(createdRecruiter);
    }

    public ResponseEntity<Recruiter> updateRecruiter(Long id, Recruiter recruiter) {
        return recruiterRepository.findById(id)
                .map(existingRecruiter -> { existingRecruiter.setName(recruiter.getName());
                    Recruiter updatedRecruiter = recruiterRepository.save(existingRecruiter);
                    return ResponseEntity.ok(updatedRecruiter);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteRecruiter(Long id) {
        Optional<Recruiter> existingRecruiter = recruiterRepository.findById(id);
        if (existingRecruiter.isPresent()) {
            recruiterRepository.delete(existingRecruiter.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Page<Recruiter>> findRecruitersByName(String keyword, Pageable page) {
        Page<Recruiter> recruits = recruiterRepository.findByNameContainsOrderByNameAsc(keyword, page);
        return ResponseEntity.ok(recruits);
    }
    
    public Long recruiterCount() {
    	return recruiterRepository.Count();
    }
}
