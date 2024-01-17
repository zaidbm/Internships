package com.internship.app.web;

import com.internship.app.entities.Intern;
import com.internship.app.services.InternService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interns")
public class InternController {

    private final InternService internService;

    @Autowired
    public InternController(InternService internService) {
        this.internService = internService;
    }

    @GetMapping
    public ResponseEntity<Page<Intern>> getAllInterns(Pageable pageable) {
        return internService.getAllInterns(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Intern> getInternById(@PathVariable Long id) {
        return internService.getInternById(id);
    }

    @PostMapping
    public ResponseEntity<Intern> createIntern(@RequestBody Intern intern) {
        return internService.createIntern(intern);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Intern> updateIntern(@PathVariable Long id, @RequestBody Intern intern) {
        return internService.updateIntern(id, intern);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntern(@PathVariable Long id) {
        return internService.deleteIntern(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Intern>> findInternsByName(@RequestParam String keyword, Pageable page) {
        return internService.findInternsByName(keyword, page);
    }
}

