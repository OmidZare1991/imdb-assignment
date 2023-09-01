package com.imdb.main.mapper;

import com.imdb.main.domain.Movie;
import com.imdb.main.domain.dto.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "Spring")
public interface MovieProcessorMapper {
    @Mapping(target = "id", qualifiedByName = "getId", source = "movieDto")
    @Mapping(target = "adult", qualifiedByName = "getBooleanValue", source = "movieDto.adult")
    Movie toMovieDto(MovieDto movieDto);

    @Named("getId")
    default String getId(MovieDto movieDto) {
        return movieDto.getId();
    }

    @Named("getBooleanValue")
    default boolean getBooleanValue(String value) {
        return "1".equals(value);
    }
}
