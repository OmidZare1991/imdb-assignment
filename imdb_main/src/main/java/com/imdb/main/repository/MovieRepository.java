package com.imdb.main.repository;

import com.imdb.main.domain.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findByGenresContaining(String genre, Pageable pageable);
}
