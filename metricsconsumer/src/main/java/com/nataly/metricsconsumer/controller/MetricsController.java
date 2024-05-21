package com.nataly.metricsconsumer.controller;

import com.nataly.metricsconsumer.dto.MetricsDto;
import com.nataly.metricsconsumer.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService service;

    @Operation(summary = "Получение метрики по идентификатору")
    @GetMapping("/metrics/{id}")
    public ResponseEntity<MetricsDto> getMetricsById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMetricsById(id));
    }

    @Operation(summary = "Получение всех метрик")
    @GetMapping("/metrics")
    public ResponseEntity<List<MetricsDto>> getAllMetrics() {
        return ResponseEntity.ok(service.getAllMetrics());
    }
}
