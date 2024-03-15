package com.internship.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import com.internship.app.dao.InternRepository;
import com.internship.app.entities.Intern;
import com.internship.app.services.InternService;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InternServiceTest {

    @Mock
    private InternRepository internRepository;
    
    @Mock
    Pageable pageable;
    
    @Mock
    Page page;

    @InjectMocks
    private InternService internService;

    @Test
    void testGetAllInterns() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        Page<Intern> mockPage = mock(Page.class);
        when(internRepository.findAll(pageable)).thenReturn(page);
        
        ResponseEntity<Page<Intern>> response = internService.getAllInterns(pageable);

        assertEquals(ResponseEntity.ok(page), response);
        verify(internRepository).findAll(pageable);
    }

    @Test
    void testGetInternByIdFound() {
        // Arrange
        Long id = 1L;
        Intern mockIntern = mock(Intern.class);
        when(internRepository.findById(id)).thenReturn(Optional.of(mockIntern));

        // Act
        ResponseEntity<Intern> response = internService.getInternById(id);

        // Assert
        assertEquals(ResponseEntity.ok(mockIntern), response);
        verify(internRepository).findById(id);
    }

    @Test
    void testGetInternByIdNotFound() {
        // Arrange
        Long id = 1L;
        when(internRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Intern> response = internService.getInternById(id);

        // Assert
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(internRepository).findById(id);
    }


    @Test
    void testCreateIntern() {
        Intern mockIntern = mock(Intern.class);
        when(internRepository.save(mockIntern)).thenReturn(mockIntern);

        ResponseEntity<Intern> response = internService.createIntern(mockIntern);

        assertEquals(ResponseEntity.ok(mockIntern), response);
        verify(internRepository).save(mockIntern);
    }

    @Test
    void testUpdateInternFound() {
        Long id = 1L;
        Intern existingIntern = mock(Intern.class);
        Intern updatedIntern = mock(Intern.class);
        when(internRepository.findById(id)).thenReturn(Optional.of(existingIntern));
        when(internRepository.save(existingIntern)).thenReturn(updatedIntern);

        ResponseEntity<Intern> response = internService.updateIntern(id, existingIntern);

        assertEquals(ResponseEntity.ok(updatedIntern), response);
        verify(internRepository).findById(id);
        verify(internRepository).save(existingIntern);
    }

    @Test
    void testUpdateInternNotFound() {
        // Arrange
        Long id = 1L;
        when(internRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Intern> response = internService.updateIntern(id, mock(Intern.class));

        // Assert
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(internRepository).findById(id);
        verify(internRepository, never()).save(any());
    }

    @Test
    void testDeleteInternFound() {
        // Arrange
        Long id = 1L;
        Intern existingIntern = mock(Intern.class);
        when(internRepository.findById(id)).thenReturn(Optional.of(existingIntern));

        // Act
        ResponseEntity<Void> response = internService.deleteIntern(id);

        // Assert
        assertEquals(ResponseEntity.noContent().build(), response);
        verify(internRepository).findById(id);
        verify(internRepository).delete(existingIntern);
    }

    @Test
    void testDeleteInternNotFound() {
        // Arrange
        Long id = 1L;
        when(internRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = internService.deleteIntern(id);

        // Assert
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(internRepository).findById(id);
        verify(internRepository, never()).delete(any());
    }
}
