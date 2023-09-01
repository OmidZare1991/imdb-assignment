package com.imdb.main.service.processor;

import com.imdb.main.domain.Rating;
import com.imdb.main.domain.dto.RatingDto;
import com.imdb.main.mapper.RatingProcessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingItemProcessor implements ItemProcessor<RatingDto, Rating> {
    private final RatingProcessorMapper mapper;

    @Override
    public Rating process(RatingDto ratingDto) {
        return mapper.toRatingDto(ratingDto);
    }
}
