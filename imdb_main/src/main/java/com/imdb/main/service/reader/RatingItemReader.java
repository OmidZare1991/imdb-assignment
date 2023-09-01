package com.imdb.main.service.reader;

import com.imdb.main.config.ConfigProperties;
import com.imdb.main.domain.dto.RatingDto;
import com.imdb.main.mapper.RatingReaderMapper;
import com.imdb.main.repository.MovieRepository;
import com.imdb.main.service.AbstractItemReader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingItemReader extends AbstractItemReader<RatingDto> {
    private final ResourceLoader resourceLoader;
    private final MovieRepository movieRepository;
    private final ConfigProperties config;
    private int recordsRead = 0;
    private final RatingReaderMapper mapper;

    @Override
    protected RatingDto processTokens(String[] tokens) {
        RatingDto rating = mapper.toRating(tokens[0], tokens[1], tokens[2], movieRepository);
        recordsRead++;
        return rating;

    }

    @Override
    protected String getResourcePath() {
        return "classpath:/files/title.ratings.tsv";
    }

    @Override
    protected int getMaxRecords() {
        return config.getCrewReaderMaxSize();
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
