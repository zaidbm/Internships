package com.gestion.app.services;

import com.gestion.app.dao.InternshipTypeRepository;
import com.gestion.app.entities.InternshipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InternshipTypeService {

    private final InternshipTypeRepository internshipTypeRepository;

    @Autowired
    public InternshipTypeService(InternshipTypeRepository internshipTypeRepository) {
        this.internshipTypeRepository = internshipTypeRepository;
    }

    public ResponseEntity<Page<InternshipType>> getAllInternshipTypes(Pageable pageable) {
        Page<InternshipType> internshipTypes = internshipTypeRepository.findAll(pageable);
        return ResponseEntity.ok(internshipTypes);
    }

    public ResponseEntity<InternshipType> getInternshipTypeById(Long id) {
        return internshipTypeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<InternshipType> createInternshipType(InternshipType internshipType) {
        InternshipType createdInternshipType = internshipTypeRepository.save(internshipType);
        return ResponseEntity.ok(createdInternshipType);
    }

    public ResponseEntity<InternshipType> updateInternshipType(Long id, InternshipType internshipType) {
        return internshipTypeRepository.findById(id)
                .map(existingInternshipType -> {
                    existingInternshipType.setName(internshipType.getName());
                    InternshipType updatedInternshipType = internshipTypeRepository.save(existingInternshipType);
                    return ResponseEntity.ok(updatedInternshipType);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteInternshipType(Long id) {
        Optional<InternshipType> existingInternshipType = internshipTypeRepository.findById(id);
        if (existingInternshipType.isPresent()) {
            internshipTypeRepository.delete(existingInternshipType.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Page<InternshipType>> findInternshipTypesByName(String keyword, Pageable page) {
        Page<InternshipType> internshipTypes = internshipTypeRepository.findByTitleContainsOrderByTitleAsc(keyword, page);
        return ResponseEntity.ok(internshipTypes);
    }
}
