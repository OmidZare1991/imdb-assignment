package com.imdb.main.service.processor;

import com.imdb.main.domain.Principal;
import com.imdb.main.domain.dto.PrincipalDto;
import com.imdb.main.mapper.PrincipalProcessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrincipalItemProcessor implements ItemProcessor<PrincipalDto, Principal> {
    private final PrincipalProcessorMapper mapper;

    @Override
    public Principal process(PrincipalDto principalDto) {
        return mapper.toPrincipalDto(principalDto);
    }
}
