package com.imdb.main.mapper;

import com.imdb.main.domain.Crew;
import com.imdb.main.domain.dto.CrewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "Spring")
public interface CrewProcessorMapper {
    @Mapping(target = "id",qualifiedByName = "getId", source = "crewDto")
    Crew toCrewDto(CrewDto crewDto);

    @Named("getId")
    default String getId(CrewDto crewDto) {
        return crewDto.getId();
    }
}
