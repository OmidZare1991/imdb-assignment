package com.imdb.main.service.impl;

import com.imdb.main.domain.dto.PrincipalResourceDto;
import com.imdb.main.repository.MovieRepository;
import com.imdb.main.repository.PrincipalRepository;
import com.imdb.main.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

    private final PrincipalRepository principalRepository;
    private final MovieRepository movieRepository;

    @Override
    public List<String> getTitlesWithActors(PrincipalResourceDto dto) {
        List<String> titles = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(dto.page(), dto.size());
        List<String> movieIdsWithActor1 = principalRepository.findMovieIdsByPersonId(dto.actorId1(), pageRequest);
        List<String> movieIdsWithActor2 = principalRepository.findMovieIdsByPersonId(dto.actorId2(), pageRequest);

        movieIdsWithActor1.retainAll(movieIdsWithActor2);

        for (String movieId : movieIdsWithActor1) {
            movieRepository.findById(movieId).ifPresent(movie -> titles.add(movie.getPrimaryTitle()));
        }

        return titles;
    }
}
