package com.internship.app.web;

import com.internship.app.entities.InternshipType;
import com.internship.app.services.InternshipTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internship-types")
public class InternshipTypeController {

    private final InternshipTypeService internshipTypeService;

    @Autowired
    public InternshipTypeController(InternshipTypeService internshipTypeService) {
        this.internshipTypeService = internshipTypeService;
    }

    @GetMapping
    public ResponseEntity<Page<InternshipType>> getAllInternshipTypes(Pageable pageable) {
        return internshipTypeService.getAllInternshipTypes(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipType> getInternshipTypeById(@PathVariable Long id) {
        return internshipTypeService.getInternshipTypeById(id);
    }

    @PostMapping
    public ResponseEntity<InternshipType> createInternshipType(@RequestBody InternshipType internshipType) {
        return internshipTypeService.createInternshipType(internshipType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternshipType> updateInternshipType(@PathVariable Long id, @RequestBody InternshipType internshipType) {
        return internshipTypeService.updateInternshipType(id, internshipType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternshipType(@PathVariable Long id) {
        return internshipTypeService.deleteInternshipType(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<InternshipType>> findInternshipTypesByName(@RequestParam String keyword, Pageable page) {
        return internshipTypeService.findInternshipTypesByName(keyword, page);
    }
}
