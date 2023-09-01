package com.imdb.main.service.impl;

import com.imdb.main.domain.Crew;
import com.imdb.main.domain.Movie;
import com.imdb.main.domain.Person;
import com.imdb.main.domain.Rating;
import com.imdb.main.domain.dto.MovieBestTitleDto;
import com.imdb.main.domain.dto.MovieSamePersonsDto;
import com.imdb.main.repository.MovieRepository;
import com.imdb.main.repository.PersonRepository;
import com.imdb.main.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    @Override
    @Cacheable(value = "titles_same_director_Writer_alive")
    public List<String> findTitlesWithSameDirectorAndWriter(MovieSamePersonsDto dto) {

        PageRequest pageRequest = PageRequest.of(dto.page(), dto.size());

        return movieRepository.findAll(pageRequest)
                .stream()
                .filter(this::hasSameDirectorAndWriter)
                .map(Movie::getPrimaryTitle)
                .toList();

    }

    @Override
    @Cacheable(value = "best_title_Bye_year_genre")
    public Map<String, String> getBestTitlesByYearForGenre(MovieBestTitleDto dto) {
        PageRequest pageRequest = PageRequest.of(dto.page(), dto.size());

        Map<String, List<Movie>> moviesByYear = movieRepository
                .findByGenresContaining(dto.genre(), pageRequest)
                .stream()
                .filter(movie -> movie.getStartYear() != null && !movie.getStartYear().isEmpty())
                .collect(Collectors.groupingBy(Movie::getStartYear));

        Map<String, String> bestTitlesByYear = new HashMap<>();

        for (Map.Entry<String, List<Movie>> entry : moviesByYear.entrySet()) {
            String year = entry.getKey();
            List<Movie> moviesForYear = entry.getValue();

            String bestTitle = getBestTitleForYear(moviesForYear);
            bestTitlesByYear.put(year, bestTitle);
        }

        return bestTitlesByYear;
    }

    private String getBestTitleForYear(List<Movie> movies) {
        Movie bestMovie = movies.stream().max(Comparator.comparingDouble(this::getMovieRating)).orElse(null);

        return bestMovie != null ? bestMovie.getPrimaryTitle() : StringUtils.EMPTY;
    }

    private double getMovieRating(Movie movie) {
        Rating rating = movie.getRating();
        return rating != null ? Double.parseDouble(rating.getAverageRating()) : 0.0;
    }


    private boolean hasSameDirectorAndWriter(Movie movie) {
        List<Crew> crews = movie.getCrews();
        return crews.stream().anyMatch(this::hasPersonInBothRoles);
    }

    private boolean hasPersonInBothRoles(Crew crew) {
        List<String> directors = crew.getDirectors();
        List<String> writers = crew.getWriters();

        return directors.stream().anyMatch(person -> writers.contains(person) && isPersonAlive(person));
    }

    private boolean isPersonAlive(String personId) {
        Person person = personRepository.findById(personId).orElse(null);
        return person != null && (person.getDeathYear() == null || person.getDeathYear().isEmpty());
    }
}
