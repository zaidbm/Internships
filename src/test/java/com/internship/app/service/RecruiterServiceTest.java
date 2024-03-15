package com.internship.app.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.internship.app.dao.RecruiterRepository;
import com.internship.app.entities.Recruiter;

@ExtendWith(MockitoExtension.class)
public class RecruiterServiceTest {

    @Mock
    private RecruiterRepository recruiterRepository;

    @InjectMocks
    private RecruiterService recruiterService;

    @Test
    void getAllRecruitersTest() {
        Page<Recruiter> recruitersPage = new PageImpl<>(List.of(new Recruiter(), new Recruiter()));
        when(recruiterRepository.findAll(any(Pageable.class))).thenReturn(recruitersPage);

        ResponseEntity<Page<Recruiter>> response = recruiterService.getAllRecruiters(Pageable.unpaged());

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getTotalElements());
        verify(recruiterRepository).findAll(any(Pageable.class));
    }

    @Test
    void getRecruiterByIdTest() {
        Recruiter recruiter = new Recruiter();
        when(recruiterRepository.findById(anyLong())).thenReturn(Optional.of(recruiter));

        ResponseEntity<Recruiter> response = recruiterService.getRecruiterById(1L);

        assertNotNull(response.getBody());
        assertEquals(recruiter, response.getBody());
        verify(recruiterRepository).findById(1L);
    }

    @Test
    void createRecruiterTest() {
        Recruiter recruiter = new Recruiter();
        when(recruiterRepository.save(any(Recruiter.class))).thenReturn(recruiter);

        ResponseEntity<Recruiter> response = recruiterService.createRecruiter(new Recruiter());

        assertNotNull(response.getBody());
        assertEquals(recruiter, response.getBody());
        verify(recruiterRepository).save(any(Recruiter.class));
    }

    @Test
    void updateRecruiterTest() {
        Recruiter existingRecruiter = new Recruiter();
        existingRecruiter.setId(1L);
        existingRecruiter.setName("Existing");

        Recruiter updatedRecruiter = new Recruiter();
        updatedRecruiter.setId(1L);
        updatedRecruiter.setName("Updated");

        when(recruiterRepository.findById(1L)).thenReturn(Optional.of(existingRecruiter));
        when(recruiterRepository.save(any(Recruiter.class))).thenReturn(updatedRecruiter);

        ResponseEntity<Recruiter> response = recruiterService.updateRecruiter(1L, updatedRecruiter);

        assertNotNull(response.getBody());
        assertEquals(updatedRecruiter.getName(), response.getBody().getName());
        verify(recruiterRepository).save(any(Recruiter.class));
    }

    @Test
    void deleteRecruiterTest() {
        Recruiter recruiter = new Recruiter();
        recruiter.setId(1L);

        when(recruiterRepository.findById(1L)).thenReturn(Optional.of(recruiter));

        ResponseEntity<Void> response = recruiterService.deleteRecruiter(1L);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(recruiterRepository).delete(recruiter);
    }

    @Test
    void findRecruitersByNameTest() {
        Page<Recruiter> recruitersPage = new PageImpl<>(List.of(new Recruiter(), new Recruiter()));
        when(recruiterRepository.findByNameContainsOrderByNameAsc(anyString(), any(Pageable.class))).thenReturn(recruitersPage);

        ResponseEntity<Page<Recruiter>> response = recruiterService.findRecruitersByName("keyword", Pageable.unpaged());

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getTotalElements());
        verify(recruiterRepository).findByNameContainsOrderByNameAsc(anyString(), any(Pageable.class));
    }

    @Test
    void recruiterCountTest() {
        when(recruiterRepository.Count()).thenReturn(10L);

        Long count = recruiterService.recruiterCount();

        assertEquals(10, count);
        verify(recruiterRepository).Count();
    }
}
