package com.imdb.main.mapper;

import com.imdb.main.domain.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonReaderMapper extends BaseReaderMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "primaryName", source = "primaryName")
    @Mapping(target = "birthYear", source = "birthYear")
    @Mapping(target = "deathYear", source = "deathYear")
    @Mapping(target = "primaryProfession", qualifiedByName = "getValues", source = "primaryProfession")
    @Mapping(target = "knownForTitles", qualifiedByName = "getValues", source = "knownForTitles")
    PersonDto toPersonDto(
            String id
            ,String primaryName
            ,String birthYear
            ,String deathYear
            ,String primaryProfession
            ,String knownForTitles
    );
}
