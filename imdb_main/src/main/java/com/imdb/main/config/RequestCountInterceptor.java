package com.imdb.main.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestCountInterceptor implements HandlerInterceptor {

    private final Counter requestCounter;

    public RequestCountInterceptor(MeterRegistry meterRegistry) {
        this.requestCounter = Counter
                .builder("http.requests.count")
                .description("Total HTTP requests")
                .register(meterRegistry);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!"/totalRequestCount".equals(request.getRequestURI())) {
            requestCounter.increment();
        }
        return true;
    }
}