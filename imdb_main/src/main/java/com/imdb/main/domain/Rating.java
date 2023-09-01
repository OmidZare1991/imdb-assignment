package com.imdb.main.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Rating {
    @Id
    private String id;
    private String averageRating;
    private String numVotes;

    @OneToOne(fetch = FetchType.LAZY)
    private Movie movie;
}