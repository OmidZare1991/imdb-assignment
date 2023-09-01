package com.imdb.main.service.writer;

import com.imdb.main.domain.Person;
import com.imdb.main.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonItemWriter implements ItemWriter<Person> {
    private final PersonRepository personRepository;


    @Override
    public void write(Chunk<? extends Person> chunk) {
        personRepository.saveAll(chunk.getItems());
    }
}
