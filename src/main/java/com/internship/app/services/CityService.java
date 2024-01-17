package com.internship.app.services;

import com.internship.app.dao.CityRepository;
import com.internship.app.entities.City;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCityById(Long id) {
    	Optional<City> category=cityRepository.findById(id);
		return category;
    }
    
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }


    public Page<City> findCitiesByName(String keyword, Pageable page) {
        Page<City> cities = cityRepository.findByNameContainsOrderByNameAsc(keyword, page);
        return cities;
    }
}
