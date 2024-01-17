package com.gestion.app.web;

import com.gestion.app.entities.City;
import com.gestion.app.services.CityService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private CityService cityService;
    
    public CityController() {
    	
    }
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        return cityService.getCityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<City> createCity(@Valid @RequestBody City city) {
        City savedCity = cityService.saveCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @Valid @RequestBody City city) {
        if (!cityService.getCityById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        city.setId(id);
        City updatedCity = cityService.saveCity(city);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        if (!cityService.getCityById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<City>> findCitiesByName(@RequestParam String keyword, Pageable page) {
    	Page<City> cities = cityService.findCitiesByName(keyword, page);
        return ResponseEntity.ok(cities);
        
    }
}
