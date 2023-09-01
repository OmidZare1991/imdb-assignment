package com.imdb.main.service.reader;

import com.imdb.main.config.ConfigProperties;
import com.imdb.main.domain.dto.PersonDto;
import com.imdb.main.service.AbstractItemReader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import static com.imdb.main.util.Utility.parseMissingValues;
import static com.imdb.main.util.Utility.removeNullItem;

@Component
@RequiredArgsConstructor
public class PersonItemReader extends AbstractItemReader<PersonDto> {
    private final ResourceLoader resourceLoader;
    private final ConfigProperties config;
    private int recordsRead = 0;


    @Override
    protected PersonDto processTokens(String[] tokens) {
        PersonDto personDto = new PersonDto();
        personDto.setId(tokens[0]);
        personDto.setPrimaryName(parseMissingValues(tokens[1]));
        personDto.setBirthYear(parseMissingValues(tokens[2]));
        personDto.setDeathYear(parseMissingValues(tokens[3]));
        personDto.setPrimaryProfession(removeNullItem(tokens[4]));
        personDto.setKnownForTitles(removeNullItem(tokens[5]));
        recordsRead++;
        return personDto;
    }

    @Override
    protected String getResourcePath() {
        return "classpath:/files/name.basics.tsv";
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
