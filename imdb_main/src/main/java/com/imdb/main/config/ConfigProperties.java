package com.imdb.main.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "imdb")
@Setter
@Getter
public class ConfigProperties {
    private Long cacheExpireTimeMinute;
    private Integer movieStepBatchChunkSize;
    private Integer crewStepBatchChunkSize;
    private Integer ratingStepBatchChunkSize;
    private Integer principalsStepBatchChunkSize;
    private Integer personStepBatchChunkSize;
    private Integer movieReaderMaxSize;
    private Integer crewReaderMaxSize;
    private Integer ratingReaderMaxSize;
    private Integer principalsReaderMaxSize;
    private Integer personReaderMaxSize;
}