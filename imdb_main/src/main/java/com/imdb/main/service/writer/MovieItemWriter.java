package com.imdb.main.service.writer;

import com.imdb.main.domain.Movie;
import com.imdb.main.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MovieItemWriter implements ItemWriter<Movie> {
    private final MovieRepository movieRepository;

    @Override
    public void write(Chunk<? extends Movie> chunk) {
        movieRepository.saveAll(chunk.getItems());
    }
}
