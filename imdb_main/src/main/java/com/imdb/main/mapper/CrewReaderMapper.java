package com.imdb.main.mapper;

import com.imdb.main.domain.Movie;
import com.imdb.main.domain.dto.CrewDto;
import com.imdb.main.repository.MovieRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CrewReaderMapper extends BaseReaderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "directors", qualifiedByName = "getValues", source = "directors")
    @Mapping(target = "writers", qualifiedByName = "getValues", source = "writers")
    @Mapping(target = "movie", expression = "java(getMovie(repository,id))")
    CrewDto toCrewDto(String id, String directors, String writers, MovieRepository repository);

    @Named("getMovie")
    default Movie getMovie(MovieRepository repository, String id) {
        Optional<Movie> movie = repository.findById(id);
        return movie.orElse(null);
    }

}
