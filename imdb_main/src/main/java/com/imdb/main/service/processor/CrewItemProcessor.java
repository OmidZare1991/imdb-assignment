package com.imdb.main.service.processor;

import com.imdb.main.domain.Crew;
import com.imdb.main.domain.dto.CrewDto;
import com.imdb.main.mapper.CrewProcessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrewItemProcessor implements ItemProcessor<CrewDto, Crew> {
    private final CrewProcessorMapper mapper;
    @Override
    public Crew process(CrewDto crewDto) {
       return mapper.toCrewDto(crewDto);
    }
}
