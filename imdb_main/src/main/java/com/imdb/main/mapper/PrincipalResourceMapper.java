package com.imdb.main.mapper;

import com.imdb.main.domain.dto.PrincipalResourceDto;
import com.imdb.spec.domain.PrincipalRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface PrincipalResourceMapper {
    PrincipalResourceDto toPrincipalResourceDto(PrincipalRequest request);
}
