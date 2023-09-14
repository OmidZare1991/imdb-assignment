package com.imdb.main.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Permission(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}