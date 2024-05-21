package com.nataly.metricsproducer.controller;

import com.nataly.metricsproducer.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MetricsController {

    private final MetricsService metricsService;

    @Operation(summary = "Отправка метрик")
    @GetMapping("/metrics")
    public ResponseEntity<String> sendMetrics() {
        return ResponseEntity.ok(metricsService.getMetrics());
    }
}
