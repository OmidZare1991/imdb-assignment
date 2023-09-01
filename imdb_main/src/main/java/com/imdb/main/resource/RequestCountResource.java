package com.imdb.main.resource;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCountResource {
    private final Counter requestCounter;

    public RequestCountResource(MeterRegistry meterRegistry) {
        this.requestCounter = Counter.builder("http.requests.count")
                .description("Total HTTP requests")
                .register(meterRegistry);
    }

    @GetMapping("/totalRequestCount")
    public long totalRequestCount() {
        return (long) requestCounter.count();
    }
}
