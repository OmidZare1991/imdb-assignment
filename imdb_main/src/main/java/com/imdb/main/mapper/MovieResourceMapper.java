package com.imdb.main.mapper;

import com.imdb.main.domain.dto.MovieBestTitleDto;
import com.imdb.main.domain.dto.MovieSamePersonsDto;
import com.imdb.spec.domain.MovieBestTitleRequest;
import com.imdb.spec.domain.MovieSamePersonsRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieResourceMapper {
    MovieSamePersonsDto toMovieSamePersonsDto(MovieSamePersonsRequest request);

    MovieBestTitleDto toMovieBestTitleRequest(MovieBestTitleRequest request);
}
