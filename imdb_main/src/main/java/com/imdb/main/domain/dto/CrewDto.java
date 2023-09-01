package com.imdb.main.domain.dto;

import com.imdb.main.domain.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CrewDto {
    private String id;
    private List<String> directors = new ArrayList<>();
    private List<String> writers = new ArrayList<>();
    private Movie movie;
}
