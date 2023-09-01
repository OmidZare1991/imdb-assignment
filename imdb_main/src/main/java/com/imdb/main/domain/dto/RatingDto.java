package com.imdb.main.domain.dto;

import com.imdb.main.domain.Movie;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RatingDto {
    private String id;
    private String averageRating;
    private String numVotes;
    private Movie movie;
}
