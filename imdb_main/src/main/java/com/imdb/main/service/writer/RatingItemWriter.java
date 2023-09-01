package com.imdb.main.service.writer;

import com.imdb.main.domain.Rating;
import com.imdb.main.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RatingItemWriter implements ItemWriter<Rating> {
    private final RatingRepository repository;

    @Override
    public void write(Chunk<? extends Rating> chunk) {
        repository.saveAll(chunk.getItems());
    }
}
