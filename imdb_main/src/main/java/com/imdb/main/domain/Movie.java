package com.imdb.main.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = @Index(columnList = "id,primaryTitle"))
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Movie {
    @Id
    private String id;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private String startYear;
    private String endYear;
    private String runtimeMinutes;

    @ElementCollection
    private List<String> genres = new ArrayList<>();

    @OneToOne(mappedBy = "movie", fetch = FetchType.LAZY)
    private Rating rating;
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Crew> crews = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<Principal> principals = new ArrayList<>();
}