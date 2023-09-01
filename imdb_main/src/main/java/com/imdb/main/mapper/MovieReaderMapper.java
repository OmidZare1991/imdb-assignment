package com.imdb.main.mapper;

import com.imdb.main.domain.dto.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")


public interface MovieReaderMapper extends BaseReaderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "titleType", qualifiedByName = "parsedData",source = "titleType")
    @Mapping(target = "primaryTitle", qualifiedByName = "parsedData", source = "primaryTitle")
    @Mapping(target = "originalTitle", qualifiedByName = "parsedData", source = "originalTitle")
    @Mapping(target = "adult", qualifiedByName = "parsedDataBoolean", source = "adult")
    @Mapping(target = "startYear", qualifiedByName = "parsedData", source = "startYear")
    @Mapping(target = "endYear", qualifiedByName = "parsedData", source = "endYear")
    @Mapping(target = "runtimeMinutes", qualifiedByName = "parsedData", source = "runtimeMinutes")
    @Mapping(target = "genres", qualifiedByName = "getValues", source = "genres")
    MovieDto toMovieDto(
            String id
            , String titleType
            , String primaryTitle
            , String originalTitle
            , String adult
            , String startYear
            , String endYear
            , String runtimeMinutes
            , String genres
    );

}
