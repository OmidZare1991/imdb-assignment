package com.imdb.main.mapper;

import com.imdb.main.domain.Person;
import com.imdb.main.domain.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "Spring")
public interface PersonProcessorMapper {
    @Mapping(target = "id", qualifiedByName = "getId", source = "personDto")
    Person toPersonDto(PersonDto personDto);

    @Named("getId")
    default String getId(PersonDto personDto) {
        return personDto.getId();
    }
}
