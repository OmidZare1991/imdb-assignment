package com.imdb.main.resource;

import com.imdb.main.mapper.PrincipalResourceMapper;
import com.imdb.main.service.PrincipalService;
import com.imdb.spec.domain.PrincipalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PrincipalResource {
    private final PrincipalService principalService;
    private final PrincipalResourceMapper mapper;

    @GetMapping("/titles-with-actors")
    public ResponseEntity<List<String>> getTitlesWithActors(PrincipalRequest request) {
        List<String> titles = principalService.getTitlesWithActors(mapper.toPrincipalResourceDto(request));
        return ResponseEntity.ok(titles);
    }
}

