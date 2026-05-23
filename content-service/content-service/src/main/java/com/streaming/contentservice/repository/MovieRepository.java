package com.streaming.contentservice.repository;

import com.streaming.contentservice.model.Genre;
import com.streaming.contentservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findByGenre(Genre genre);
    List<Movie> findByTitleContainingCase(String title);
}
