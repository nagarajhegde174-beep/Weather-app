package com.weather.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
@Slf4j
public class HealthController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {
        log.info("GET /api/health");
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "Weather Backend",
                "timestamp", LocalDateTime.now().toString(),
                "version", "1.0.0"
        ));
    }
}
