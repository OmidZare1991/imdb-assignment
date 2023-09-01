package com.imdb.main.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(indexes = @Index(columnList = "id"))
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Principal {
    @Id
    private String id;
    private String ordering;
    private String category;
    private String job;
    private String characters;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
}