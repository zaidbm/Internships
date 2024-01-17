package com.gestion.app.web;

import com.gestion.app.entities.Internship;
import com.gestion.app.services.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    private final InternshipService internshipService;

    @Autowired
    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @GetMapping
    public ResponseEntity<Page<Internship>> getAllInternships(Pageable pageable) {
        return internshipService.getAllInternships(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Internship> getInternshipById(@PathVariable Long id) {
        return internshipService.getInternshipById(id);
    }

    @PostMapping
    public ResponseEntity<Internship> createInternship(@RequestBody Internship internship) {
        return internshipService.createInternship(internship);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Internship> updateInternship(@PathVariable Long id, @RequestBody Internship internship) {
        return internshipService.updateInternship(id, internship);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternship(@PathVariable Long id) {
        return internshipService.deleteInternship(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Internship>> findInternshipsByTitle(@RequestParam String keyword, Pageable page) {
        return internshipService.findInternshipsByTitle(keyword, page);
    }
}

