package com.imdb.spec.domain;

public record PrincipalRequest(Integer page, Integer size, String actorId1, String actorId2) {
}
