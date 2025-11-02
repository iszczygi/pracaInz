package com.example.pracaInz.repository;

import com.example.pracaInz.classes.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("SELECT DISTINCT l.country FROM Location l")
    List<String> findAllCountries();

    @Query("SELECT DISTINCT l.city FROM Location l WHERE LOWER(l.country) = LOWER(:country)")
    List<String> findAllCitiesByCountry(@Param("country") String country);

    @Query("SELECT DISTINCT l.university FROM Location l WHERE LOWER(l.city) = LOWER(:city)")
    List<String> findAllUniversitiesByCity(@Param("city") String city);

}
