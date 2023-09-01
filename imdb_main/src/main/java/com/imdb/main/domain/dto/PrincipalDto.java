package com.imdb.main.domain.dto;

import com.imdb.main.domain.Movie;
import com.imdb.main.domain.Person;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PrincipalDto {

    private String id;
    private String ordering;
    private String category;
    private String job;
    private String characters;
    private Movie movie;
    private Person person;
}
