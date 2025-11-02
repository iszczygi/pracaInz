package com.example.pracaInz.services;


import com.example.pracaInz.classes.Location;
import com.example.pracaInz.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {
    private final LocationRepository locationRepository;

    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    public List<String> getAllCountries() {
        return locationRepository.findAllCountries();
    }

    public List<String> getCitiesByCountry(String country) {
        return locationRepository.findAllCitiesByCountry(country);
    }

    public List<String> getUniversitiesByCity(String city) {
        return locationRepository.findAllUniversitiesByCity(city);
   }

}
