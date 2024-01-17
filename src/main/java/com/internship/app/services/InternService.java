package com.internship.app.services;

import com.internship.app.dao.InternRepository;
import com.internship.app.entities.Intern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InternService {

    private final InternRepository internRepository;

    @Autowired
    public InternService(InternRepository internRepository) {
        this.internRepository = internRepository;
    }

    public ResponseEntity<Page<Intern>> getAllInterns(Pageable pageable) {
        Page<Intern> interns = internRepository.findAll(pageable);
        return ResponseEntity.ok(interns);
    }

    public ResponseEntity<Intern> getInternById(Long id) {
        return internRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Intern> createIntern(Intern intern) {
        Intern createdIntern = internRepository.save(intern);
        return ResponseEntity.ok(createdIntern);
    }

    public ResponseEntity<Intern> updateIntern(Long id, Intern intern) {
        return internRepository.findById(id)
                .map(existingIntern -> {
                    // Update relevant fields
                    existingIntern.setName(intern.getName());
                    // ...
                    Intern updatedIntern = internRepository.save(existingIntern);
                    return ResponseEntity.ok(updatedIntern);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteIntern(Long id) {
        Optional<Intern> existingIntern = internRepository.findById(id);
        if (existingIntern.isPresent()) {
            internRepository.delete(existingIntern.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Page<Intern>> findInternsByName(String keyword, Pageable page) {
        Page<Intern> interns = internRepository.findByNameContainsOrderByNameAsc(keyword, page);
        return ResponseEntity.ok(interns);
    }
    
    public Long InternsCount() {
    	return internRepository.Count();
    }
}
