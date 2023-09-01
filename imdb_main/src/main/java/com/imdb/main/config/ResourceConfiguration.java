package com.imdb.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ResourceConfiguration {

    @Value("classpath:")
    private Resource resourceBase;

    @Bean
    public Resource resource() {
        return resourceBase;
    }
}