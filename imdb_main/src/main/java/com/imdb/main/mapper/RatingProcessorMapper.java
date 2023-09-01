package com.imdb.main.mapper;

import com.imdb.main.domain.Rating;
import com.imdb.main.domain.dto.RatingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "Spring")
public interface RatingProcessorMapper {
    @Mapping(target = "id", qualifiedByName = "getId", source = "ratingDto")
    Rating toRatingDto(RatingDto ratingDto);

    @Named("getId")
    default String getId(RatingDto ratingDto) {
        return ratingDto.getId();
    }
}
