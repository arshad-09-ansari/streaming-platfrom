package com.streaming.contentservice.controller;

import com.streaming.contentservice.dto.MovieRequest;
import com.streaming.contentservice.dto.MovieResponse;
import com.streaming.contentservice.model.Genre;
import com.streaming.contentservice.model.Movie;
import com.streaming.contentservice.service.ContentSerivce;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@Slf4j
@RequiredArgsConstructor
public class ContentController {

    private final ContentSerivce contentSerivce;

// add new movie to catlog
    @PostMapping
    public ResponseEntity<MovieResponse> addMovie(
            @Valid @RequestBody MovieRequest movieRequest
            ){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(contentSerivce.addMovie(movieRequest));
    }

    //get all movies
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        return ResponseEntity.ok(contentSerivce.getAllMovies());
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieResponse>> getMovieByGenre(
            @PathVariable Genre genre
            ){
        return ResponseEntity.ok(contentSerivce.getMovieByGenre(genre));

    }

    //get movie byu id
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieResponse> getMovieById(
            @PathVariable String movieId
    ){
        return ResponseEntity.ok(contentSerivce.getMovieById(movieId));

    }


    @GetMapping("/search")
    public  ResponseEntity<List<MovieResponse>> searchMovie(
            @RequestParam String title
    ){
        return  ResponseEntity.ok(contentSerivce.searchMovie(title));
    }
}
