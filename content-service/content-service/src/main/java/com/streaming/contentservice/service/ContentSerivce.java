package com.streaming.contentservice.service;

import com.streaming.contentservice.dto.MovieRequest;
import com.streaming.contentservice.dto.MovieResponse;
import com.streaming.contentservice.model.Genre;
import com.streaming.contentservice.model.Movie;
import com.streaming.contentservice.model.VideoStatus;
import com.streaming.contentservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentSerivce {
    private final MovieRepository movieRepository;


    /**
     * add a new movie to catlog
     * cideo isnot upload yet at this stage
     */

    public MovieResponse addMovie(MovieRequest request) {
        log.info("Adding new movie: {}", request.getTitle());
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setGenre(request.getGenre());
        movie.setDirector(request.getDirector());
        movie.setCast(request.getCast());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setRating(Double.parseDouble(request.getRating()));
        movie.setThumbnailUrl(request.getThumbnailUrl());
        movie.setDurationInMinutes(request.getDurationMinutes());
        movie.setVideoStatus(VideoStatus.PENDEING);

        Movie savedMovie = movieRepository.save(movie);
        log.info("movie added with Id:{}", savedMovie.getId());

        return mapToResponse(savedMovie);


    }

    private MovieResponse mapToResponse(Movie movie) {
        MovieResponse response = new MovieResponse();
        response.setId(movie.getId());
        response.setTitle(movie.getTitle());
        response.setDescription(movie.getDescription());
        response.setGenre(movie.getGenre());
        response.setDirector(movie.getDirector());
        response.setCast(movie.getCast());
        response.setReleaseYear(movie.getReleaseYear());
        response.setRating(movie.getRating());
        response.setThumbnailUrl(movie.getThumbnailUrl());
        response.setDurationMinutes(movie.getDurationInMinutes());
        response.setVideoStatus(movie.getVideoStatus());
        response.setHlsUrl(movie.getHlsUrl());
        response.setCreatedAt(movie.getCreatedAt());


        return response;
    }

    /***
     * Get all movies in the catlog
     */
    public List<MovieResponse> getAllMovies(){
        return movieRepository.findAll()
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /***
     * Get movie by Id
     */
    public MovieResponse getMovieById(String movieId){
        Movie movie = movieRepository.findById(movieId).orElseThrow(()->new RuntimeException("Movie not found: " + movieId));
        return mapToResponse(movie);
    }

    /***
     * get movies by genre
     */
    public List<MovieResponse> getMovieByGenre(Genre genre) {

        List<Movie> movies = movieRepository.findByGenre(genre);

        return movies.stream()
                .map(this::mapToResponse)
                .toList();
    }

    /***
     *search movie by title
     * @param title
     * @return
     */
    public List<MovieResponse> searchMovie(String title){
        return movieRepository.findByTitleContainingCase(title)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }
}




