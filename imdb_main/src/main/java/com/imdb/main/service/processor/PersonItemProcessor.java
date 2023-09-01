package com.imdb.main.service.processor;

import com.imdb.main.domain.Person;
import com.imdb.main.domain.dto.PersonDto;
import com.imdb.main.mapper.PersonProcessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonItemProcessor implements ItemProcessor<PersonDto, Person> {
    private final PersonProcessorMapper mapper;
    @Override
    public Person process(PersonDto personDto) {
        return mapper.toPersonDto(personDto);
    }
}
