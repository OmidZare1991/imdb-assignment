package com.imdb.main.service.processor;

import com.imdb.main.domain.Movie;
import com.imdb.main.domain.dto.MovieDto;
import com.imdb.main.mapper.MovieProcessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieItemProcessor implements ItemProcessor<MovieDto, Movie> {
    private final MovieProcessorMapper mapper;

    @Override
    public Movie process(MovieDto movieDto) {
        return mapper.toMovieDto(movieDto);
    }
}
