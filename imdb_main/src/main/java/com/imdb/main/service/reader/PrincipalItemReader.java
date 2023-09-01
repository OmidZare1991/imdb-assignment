package com.imdb.main.service.reader;

import com.imdb.main.config.ConfigProperties;
import com.imdb.main.domain.dto.PrincipalDto;
import com.imdb.main.mapper.PrincipalReaderMapper;
import com.imdb.main.repository.MovieRepository;
import com.imdb.main.repository.PersonRepository;
import com.imdb.main.service.AbstractItemReader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrincipalItemReader extends AbstractItemReader<PrincipalDto> {
    private final ResourceLoader resourceLoader;
    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;
    private final ConfigProperties config;
    private int recordsRead = 0;
    private final PrincipalReaderMapper mapper;

    @Override
    protected PrincipalDto processTokens(String[] tokens) {

        PrincipalDto principalDto = mapper.toPrincipalDto(
                tokens[0]
                , tokens[1]
                , tokens[2]
                , tokens[3]
                , tokens[4]
                , tokens[5]
                , movieRepository
                , personRepository
        );
        recordsRead++;
        return principalDto;
    }

    @Override
    protected String getResourcePath() {
        return "classpath:/files/title.principals.tsv";
    }

    @Override
    protected int getMaxRecords() {
        return config.getPersonReaderMaxSize();
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
