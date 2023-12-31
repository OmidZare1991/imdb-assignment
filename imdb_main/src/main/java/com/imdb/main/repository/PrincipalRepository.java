package com.imdb.main.repository;

import com.imdb.main.domain.Principal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Long> {
    @Query("SELECT p.movie.id FROM Principal p WHERE p.person.id = :personId")
    List<String> findMovieIdsByPersonId(@Param("personId") String personId, Pageable pageable);
}
