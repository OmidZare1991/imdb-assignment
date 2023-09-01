package com.imdb.main.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = @Index(columnList = "id,deathYear"))
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Person {
    @Id
    private String id;
    private String primaryName;
    private String birthYear;
    private String deathYear;

    @ElementCollection
    private List<String> primaryProfession = new ArrayList<>();
    @ElementCollection
    private List<String> knownForTitles = new ArrayList<>();
    @OneToMany(mappedBy = "person")
    private List<Principal> principals = new ArrayList<>();
}





