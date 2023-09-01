package com.imdb.main.service;

import com.imdb.main.domain.dto.PrincipalResourceDto;

import java.util.List;

public interface PrincipalService {
    List<String> getTitlesWithActors(PrincipalResourceDto dto);
}
