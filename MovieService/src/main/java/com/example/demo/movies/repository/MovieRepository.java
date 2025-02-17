package com.example.demo.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.movies.entity.Movies;

@Repository  // Specifies that this interface is a repository.
public interface MovieRepository extends JpaRepository<Movies, Integer> {
    // No additional methods required; JpaRepository provides CRUD operations.
}
