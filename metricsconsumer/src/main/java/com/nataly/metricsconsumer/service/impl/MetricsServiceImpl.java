package com.nataly.metricsconsumer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nataly.metricsconsumer.dto.MetricsDto;
import com.nataly.metricsconsumer.exception.DataNotFoundException;
import com.nataly.metricsconsumer.mapper.MetricsDtoMapper;
import com.nataly.metricsconsumer.model.MetricsEntity;
import com.nataly.metricsconsumer.repository.MetricsEntityRepository;
import com.nataly.metricsconsumer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsServiceImpl implements MetricsService {

    private final MetricsDtoMapper mapper;
    private final MetricsEntityRepository repository;
    private final ObjectMapper objectMapper;

    @Override
    public void saveMetrics(String metrics) {
        try {
            var metricsEntity = objectMapper.readValue(metrics, MetricsEntity.class);
            log.info("Get metricsEntity for save");
            repository.save(metricsEntity);

        } catch (JsonProcessingException e) {
            log.error("Error parsing JSON string: {}", metrics);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<MetricsDto> getAllMetrics() {
        return mapper.toListMetricsDto(repository.findAll());
    }

    @Override
    public MetricsDto getMetricsById(Long id) {
        return mapper.toMetricsDto(repository.findById(id).orElseThrow(()-> new DataNotFoundException("Metrics with id " + id + " not found")));
    }
}
