package com.imdb.main.mapper;

import com.imdb.main.domain.Movie;
import com.imdb.main.domain.Person;
import com.imdb.main.domain.dto.PersonDto;
import com.imdb.main.domain.dto.PrincipalDto;
import com.imdb.main.repository.MovieRepository;
import com.imdb.main.repository.PersonRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PrincipalReaderMapper extends BaseReaderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ordering", qualifiedByName = "parsedData", source = "ordering")
    @Mapping(target = "category", qualifiedByName = "parsedData", source = "category")
    @Mapping(target = "job", qualifiedByName = "parsedData", source = "job")
    @Mapping(target = "characters", qualifiedByName = "parsedData",source = "characters")
    @Mapping(target = "movie", expression = "java(getMovie(movieRepository,id))")
    @Mapping(target = "person", expression = "java(getPerson(personRepository,personId))")
    PrincipalDto toPrincipalDto(
            String id
            , String ordering
            , String personId
            , String category
            , String job
            , String characters
            , MovieRepository movieRepository
            , PersonRepository personRepository
    );
    @Named("getMovie")
    default Movie getMovie(MovieRepository repository, String id) {
        Optional<Movie> movie = repository.findById(id);
        return movie.orElse(null);
    }

    @Named("getPerson")
    default Person getPerson(PersonRepository personRepository, String id){
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }
}
