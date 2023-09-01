package com.imdb.main.mapper;

import com.imdb.main.domain.Movie;
import com.imdb.main.domain.Rating;
import com.imdb.main.domain.dto.RatingDto;
import com.imdb.main.repository.MovieRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface RatingReaderMapper extends BaseReaderMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "averageRating", qualifiedByName = "parsedData", source = "averageRating")
    @Mapping(target = "numVotes", qualifiedByName = "parsedData", source = "numVotes")
    @Mapping(target = "movie", expression = "java(getMovie(repository,id))")
    RatingDto toRating(String id, String averageRating, String numVotes, MovieRepository repository);


    @Named("getMovie")
    default Movie getMovie(MovieRepository repository, String id) {
        Optional<Movie> movie = repository.findById(id);
        return movie.orElse(null);
    }
}
