package com.example.pracaInz.controllers;

import com.example.pracaInz.classes.Location;
import com.example.pracaInz.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/api/locations/countries")
    @ResponseBody
    public List<String> getAllCountries() {
        return locationService.getAllCountries();
    }

    @GetMapping("/api/locations/countries/{country}/cities")
    @ResponseBody
    public List<String> getCitiesByCountry(@PathVariable String country) {
        return locationService.getCitiesByCountry(country);
    }

    @GetMapping("/api/locations/cities/{city}/universities")
    @ResponseBody
    public List<String> getUniversitiesByCity(@PathVariable String city) {
        return locationService.getUniversitiesByCity(city);
    }

    @GetMapping("/location_write")
    public String showLocationWriteForm() {
        return "location_write"; // Return the location_write.html template
    }

    @PostMapping("/location_write")
    public String submitLocationWriteForm(@ModelAttribute Location location) {
        locationService.saveLocation(location);
        return "redirect:/home"; // Redirect to home page after submission
    }
}