package com.nataly.metricsconsumer.service;

import com.nataly.metricsconsumer.dto.MetricsDto;

import java.util.List;

public interface MetricsService {

    void saveMetrics(String metrics);

    List<MetricsDto> getAllMetrics();

    MetricsDto getMetricsById(Long id);
}
