package com.imdb.main.service.reader;

import com.imdb.main.config.ConfigProperties;
import com.imdb.main.domain.dto.MovieDto;
import com.imdb.main.mapper.MovieReaderMapper;
import com.imdb.main.service.AbstractItemReader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieItemReader extends AbstractItemReader<MovieDto> {

    private final ResourceLoader resourceLoader;
    private final ConfigProperties config;
    private final MovieReaderMapper mapper;
    private int recordsRead = 0;

    @Override
    protected MovieDto processTokens(String[] tokens) {
        MovieDto movieDto = mapper.toMovieDto(
                tokens[0]
                , tokens[1]
                , tokens[2]
                , tokens[3]
                , tokens[4]
                , tokens[5]
                , tokens[6]
                , tokens[7]
                , tokens[8]
        );
        recordsRead++;
        return movieDto;
    }

    @Override
    protected String getResourcePath() {
        return "classpath:/files/title.basics.tsv";
    }

    @Override
    protected int getMaxRecords() {
        return config.getMovieReaderMaxSize();
    }

    @Override
    protected ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
    @Override
    protected int getRecordsRead() {
        return recordsRead;
    }
}