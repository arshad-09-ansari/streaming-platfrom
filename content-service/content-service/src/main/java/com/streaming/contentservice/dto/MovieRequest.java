package com.streaming.contentservice.dto;

import com.streaming.contentservice.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    @NotBlank(message = "this is required")
  private String title;
  private String description;
  @NotNull(message = "Genre is required")
  private Genre genre;

  private String director;
  private  String cast;
  private int releaseYear;
  private String rating;
  private  String thumbnailUrl;
  private int durationMinutes;

}
