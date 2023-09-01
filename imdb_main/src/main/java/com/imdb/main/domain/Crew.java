package com.imdb.main.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = @Index(columnList = "id"))
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Crew {
    @Id
    private String id;

    @ElementCollection
    private List<String> directors = new ArrayList<>();

    @ElementCollection
    private List<String> writers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}