package com.internship.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.internship.app.dao.CityRepository;
import com.internship.app.entities.City;
import com.internship.app.services.CityService;

@ExtendWith(MockitoExtension.class)
public class cityServiceTest {

	@Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    

    @Test
    void testGetAllCities() {
        List<City> cities = new ArrayList<>();
        // Add some sample cities to the list
        cities.add(new City("city1"));
        cities.add(new City("city2"));
        
        when(cityRepository.findAll()).thenReturn(cities);

        List<City> result = cityService.getAllCities();

        assertEquals(cities, result);
    }

    @Test
    void testGetCityById() {
        Long cityId = 1L;
        City city = new City("city1");
        city.setId(cityId);
        
        
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));

        Optional<City> result = cityService.getCityById(cityId);

        assertEquals(Optional.of(city), result);
    }
    
    @Test
    void testGetCategoryByIdNotFound() {
        Long cityId = 2L;

        when(cityRepository.findById(cityId)).thenReturn(Optional.empty());

        // Act
        Optional<City> result = cityService.getCityById(cityId);

        // Assert
        assertEquals(result,Optional.empty());

        // Verify that the findById method was called with the correct argument
        verify(cityRepository).findById(cityId);
    }

    @Test
    void testSaveCity() {
        City city = new City("city1");

        when(cityRepository.save(city)).thenReturn(city);

        City result = cityService.saveCity(city);

        assertEquals(city, result);
    }

    @Test
    void testDeleteCity() {
        Long cityId = 1L;

        cityService.deleteCity(cityId);

        verify(cityRepository).deleteById(cityId);
    }

    @Test
    void testFindCitiesByName() {
        String keyword = "test";
        List<City> cities=new ArrayList<>();
        cities.add(new City("city1"));
        cities.add(new City("city2"));
        Page<City> citiesPage =new PageImpl<>(cities);

        when(cityRepository.findByNameContainsOrderByNameAsc(keyword,PageRequest.of(0,10))).thenReturn(citiesPage);
        
        Page<City> result = cityService.findCitiesByName(keyword, PageRequest.of(0,10));

        assertEquals(citiesPage, result);
    }
}
