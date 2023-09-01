package com.imdb.main.domain.dto;

import com.imdb.main.domain.Principal;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PersonDto {
    private String id;
    private String primaryName;
    private String birthYear;
    private String deathYear;
    private List<String> primaryProfession = new ArrayList<>();
    private List<String> knownForTitles = new ArrayList<>();
    private List<Principal> principals = new ArrayList<>();
}
