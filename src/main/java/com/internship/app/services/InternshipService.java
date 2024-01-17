package com.internship.app.services;

import com.internship.app.dao.InternshipRepository;
import com.internship.app.dao.InternshipTypeRepository;
import com.internship.app.dao.RecruiterRepository;
import com.internship.app.dto.InternshipCountPerRecruiter;
import com.internship.app.dto.InternshipsCountByType;
import com.internship.app.entities.Internship;
import com.internship.app.entities.InternshipType;
import com.internship.app.entities.Recruiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class InternshipService {

    private InternshipRepository internshipRepository;
    private RecruiterRepository recruiterRepository;
    private InternshipTypeRepository internshipTypeRepository;

    @Autowired
    public InternshipService(InternshipRepository internshipRepository,RecruiterRepository recruiterRepository
    		,InternshipTypeRepository internshipTypeRepository) {
        this.internshipRepository = internshipRepository;
        this.recruiterRepository=recruiterRepository;
        this.internshipTypeRepository=internshipTypeRepository;
    }

    public ResponseEntity<Page<Internship>> getAllInternships(Pageable pageable) {
        Page<Internship> internships = internshipRepository.findAll(pageable);
        return ResponseEntity.ok(internships);
    }

    public ResponseEntity<Internship> getInternshipById(Long id) {
        return internshipRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Internship> createInternship(Internship internship) {
        Internship createdInternship = internshipRepository.save(internship);
        return ResponseEntity.ok(createdInternship);
    }

    public ResponseEntity<Internship> updateInternship(Long id, Internship internship) {
        return internshipRepository.findById(id)
                .map(existingInternship -> {
                    // Update relevant fields
                    existingInternship.setTitle(internship.getTitle());
                    // ...
                    Internship updatedInternship = internshipRepository.save(existingInternship);
                    return ResponseEntity.ok(updatedInternship);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteInternship(Long id) {
        Optional<Internship> existingInternship = internshipRepository.findById(id);
        if (existingInternship.isPresent()) {
            internshipRepository.delete(existingInternship.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Page<Internship>> findInternshipsByTitle(String keyword, Pageable page) {
        Page<Internship> internships = internshipRepository.findByTitleContainsOrderByTitleAsc(keyword, page);
        return ResponseEntity.ok(internships);
    }
    public Long Count(){
        return internshipRepository.Count();
    }

    public List<InternshipCountPerRecruiter> internshipCountPerRecruiter() {
        List<Recruiter> recruiters = recruiterRepository.findAll();
        List<InternshipCountPerRecruiter> internshipCount=new ArrayList<>();
        for (Recruiter recruiter : recruiters) {
        	internshipCount.add(new InternshipCountPerRecruiter((long) recruiter.getInternships().size(),recruiter.getName()));
        }
        return internshipCount;
    }
    
    public List<InternshipsCountByType> internshipsCountByType() {
        List<InternshipType> types = internshipTypeRepository.findAll();
        List<InternshipsCountByType> internshipCount=new ArrayList<>();
        for (InternshipType type : types) {
        	internshipCount.add(new InternshipsCountByType((long) type.getInternships().size(),type.getName()));
        }
        return internshipCount;
    }
}
