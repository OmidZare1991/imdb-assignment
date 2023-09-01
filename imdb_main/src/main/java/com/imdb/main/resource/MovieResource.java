package com.imdb.main.resource;


import com.imdb.main.mapper.MovieResourceMapper;
import com.imdb.main.service.MovieService;
import com.imdb.spec.domain.MovieBestTitleRequest;
import com.imdb.spec.domain.MovieSamePersonsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieResource {
    private final MovieService movieService;
    private final MovieResourceMapper mapper;

    @GetMapping("/titles-same-director-writer-alive")
    public ResponseEntity<List<String>> getTitlesSameWriterDirectorAlive(MovieSamePersonsRequest request) {
        return ResponseEntity.ok(movieService.findTitlesWithSameDirectorAndWriter(mapper.toMovieSamePersonsDto(request)));
    }

    @GetMapping("/best-titles-by-year")
    public ResponseEntity<Map<String, String>> getBestTitlesByYear(MovieBestTitleRequest request) {
        Map<String, String> bestTitlesByYear = movieService.getBestTitlesByYearForGenre(mapper.toMovieBestTitleRequest(request));
        return ResponseEntity.ok(bestTitlesByYear);
    }

}