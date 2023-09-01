package com.imdb.main.service;

import com.imdb.main.domain.dto.MovieBestTitleDto;
import com.imdb.main.domain.dto.MovieSamePersonsDto;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<String> findTitlesWithSameDirectorAndWriter(MovieSamePersonsDto dto);

    Map<String, String> getBestTitlesByYearForGenre(MovieBestTitleDto dto);
}
