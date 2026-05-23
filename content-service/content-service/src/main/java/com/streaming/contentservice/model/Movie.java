package com.streaming.contentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.introspect.AnnotatedAndMetadata;

import java.time.LocalDateTime;


@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(updatable = false)
    private  String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String director;
    private String cast;
    private  int releaseYear;

    //s3 master playlist URL for streaming
    private double rating;

    private  String thumbnailUrl;
    private int durationInMinutes;
    private String videoKey;

    private String hlsUrl;


    //status of video procesing
    @Enumerated(EnumType.STRING)
    private VideoStatus videoStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
