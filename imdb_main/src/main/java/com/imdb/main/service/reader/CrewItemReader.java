package com.imdb.main.service.reader;

import com.imdb.main.config.ConfigProperties;
import com.imdb.main.domain.dto.CrewDto;
import com.imdb.main.mapper.CrewReaderMapper;
import com.imdb.main.repository.MovieRepository;
import com.imdb.main.service.AbstractItemReader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrewItemReader extends AbstractItemReader<CrewDto> {
    private final CrewReaderMapper mapper;
    private final MovieRepository movieRepository;
    private final ConfigProperties config;
    private final ResourceLoader resourceLoader;
    private int recordsRead = 0;

    @Override
    protected CrewDto processTokens(String[] tokens) {
        CrewDto crewDto = mapper.toCrewDto(tokens[0], tokens[1], tokens[2], movieRepository);
        recordsRead++;
        return crewDto;
    }

    @Override
    protected String getResourcePath() {
        return "classpath:/files/title.crew.tsv";
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

