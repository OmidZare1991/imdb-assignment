package com.imdb.main.mapper;

import com.imdb.main.domain.Principal;
import com.imdb.main.domain.dto.PrincipalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "Spring")
public interface PrincipalProcessorMapper {
    @Mapping(target = "id", qualifiedByName = "getId", source = "principalDto")
    Principal toPrincipalDto(PrincipalDto principalDto);

    @Named("getId")
    default String getId(PrincipalDto principalDto) {
        return principalDto.getId();
    }
}
