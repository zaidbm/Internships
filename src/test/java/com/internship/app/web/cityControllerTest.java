package com.internship.app.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.internship.app.entities.City;
import com.internship.app.services.CityService;
import com.internship.app.web.CityController;


@ExtendWith(MockitoExtension.class)
public class cityControllerTest {

	@Mock
	CityService cityService;
	
	@InjectMocks
	CityController cityController=new CityController();

	@Test
    void testGetAllCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("city1"));
        cities.add(new City("city2"));
        
        when(cityService.getAllCities()).thenReturn(cities);

        List<City> result = cityController.getAllCities();

        assertEquals(cities, result);
    }

	@Test
    void testGetCityById() {
        Long cityId = 1L;
        City city = new City("city1");
        city.setId(cityId);

        when(cityService.getCityById(cityId)).thenReturn(Optional.of(city));

        ResponseEntity<City> result = cityController.getCityById(cityId);

        assertEquals(ResponseEntity.ok(city), result);
    }
	
	@Test
    void testGetCityByIdNotFound() {
        Long cityId = 1L;

        when(cityService.getCityById(cityId)).thenReturn(Optional.empty());

        ResponseEntity<City> result = cityController.getCityById(cityId);

        assertEquals(ResponseEntity.notFound().build(), result);
    }
	
	@Test
    void testCreateCity() {
        City city = new City("city1");
        when(cityService.saveCity(city)).thenReturn(city);

        ResponseEntity<City> result = cityController.createCity(city);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(city), result);
    }

    @Test
    void testUpdateCity() {
        Long cityId = 1L;
        City city = new City("city");
        city.setId(cityId);

        when(cityService.getCityById(cityId)).thenReturn(Optional.of(city));
        when(cityService.saveCity(city)).thenReturn(city);

        ResponseEntity<City> result = cityController.updateCity(cityId, city);

        assertEquals(ResponseEntity.ok(city), result);
    }

    @Test
    void testUpdateCityNotFound() {
        Long cityId = 1L;
        City city = new City("city");

        when(cityService.getCityById(cityId)).thenReturn(Optional.empty());

        ResponseEntity<City> result = cityController.updateCity(cityId, city);

        assertEquals(ResponseEntity.notFound().build(), result);
    }
	
    @Test
    void testDeleteCity() {
        Long cityId = 1L;

        when(cityService.getCityById(cityId)).thenReturn(Optional.of(new City()));

        ResponseEntity<Void> result = cityController.deleteCity(cityId);

        assertEquals(ResponseEntity.noContent().build(), result);
        verify(cityService).deleteCity(cityId);
    }

    @Test
    void testDeleteCityNotFound() {
        Long cityId = 1L;

        when(cityService.getCityById(cityId)).thenReturn(Optional.empty());

        ResponseEntity<Void> result = cityController.deleteCity(cityId);

        assertEquals(ResponseEntity.notFound().build(), result);
        verify(cityService, never()).deleteCity(cityId);
    }

    @Test
    void testFindCitiesByName() {
        String keyword = "test";
        List<City> cities=new ArrayList<>();
        Page<City> citiesPage = new PageImpl<>(cities);

        when(cityService.findCitiesByName(keyword, PageRequest.of(0, 10))).thenReturn(citiesPage);

        ResponseEntity<Page<City>> result = cityController.findCitiesByName(keyword, PageRequest.of(0, 10));

        assertEquals(ResponseEntity.ok(cities), result);
    }
	
	
	
	
	
}
