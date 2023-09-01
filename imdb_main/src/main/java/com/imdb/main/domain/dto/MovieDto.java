package com.imdb.main.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MovieDto {
    private String id;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private Boolean adult;
    private String startYear;
    private String endYear;
    private String runtimeMinutes;
    private List<String> genres = new ArrayList<>();
}
