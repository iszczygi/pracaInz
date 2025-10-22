package com.example.pracaInz.repository;

import com.example.pracaInz.classes.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Integer> {


    // Find all opinions
    List<Opinion> findAll();

    // Find opinions by country and city
    @Query("SELECT o FROM Opinion o WHERE LOWER(o.country) = LOWER(:country) AND LOWER(o.city) = LOWER(:city)")
    List<Opinion> findByCountryAndCity(@Param("country") String country, @Param("city") String city);
    // Find opinions by country
    @Query("SELECT o FROM Opinion o WHERE LOWER(o.country) = LOWER(:country)")
    List<Opinion> findByCountry(@Param("country") String country);
    // Find opinions by city
    @Query("SELECT o FROM Opinion o WHERE LOWER(o.city) = LOWER(:city)")
    List<Opinion> findByCity(@Param("city") String city);


}
