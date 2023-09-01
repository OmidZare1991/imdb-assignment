package com.imdb.main.service.writer;

import com.imdb.main.domain.Principal;
import com.imdb.main.repository.PrincipalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PrincipalItemWriter implements ItemWriter<Principal> {
    private final PrincipalRepository principalRepository;


    @Override
    public void write(Chunk<? extends Principal> chunk) {
        principalRepository.saveAll(chunk.getItems());
    }
}
