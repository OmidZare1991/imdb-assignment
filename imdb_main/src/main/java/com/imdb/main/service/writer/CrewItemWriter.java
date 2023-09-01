package com.imdb.main.service.writer;

import com.imdb.main.domain.Crew;
import com.imdb.main.repository.CrewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CrewItemWriter implements ItemWriter<Crew> {
    private final CrewRepository crewRepository;

    @Override
    public void write(Chunk<? extends Crew> chunk) {
        crewRepository.saveAll(chunk.getItems());
    }
}
