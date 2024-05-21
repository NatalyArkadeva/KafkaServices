package com.nataly.metricsconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nataly.metricsconsumer.MetricsconsumerApplication;
import com.nataly.metricsconsumer.mapper.MetricsDtoMapper;
import com.nataly.metricsconsumer.model.MetricsEntity;
import com.nataly.metricsconsumer.repository.MetricsEntityRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = MetricsconsumerApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MetricsServiceImplTest {

    @Autowired
    private MetricsService service;
    @Autowired
    private MetricsDtoMapper mapper;
    @Autowired
    private MetricsEntityRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    private final EasyRandom random = new EasyRandom();


    @AfterEach
    void clear() {
        repository.deleteAll();
    }

    @Test
    @Transactional
    void saveMetrics() throws JsonProcessingException {
        String metrics = "{\"status\":\"UP\",\"components\":{\"diskSpace\":{\"status\":\"UP\",\"details\":{\"total\":104856547328,\"free\":33898024960,\"threshold\":10485760,\"path\":\"D:\\\\Java\\\\metricsproducer\\\\.\",\"exists\":true}},\"ping\":{\"status\":\"UP\"}}}";
        var metricsEntity = objectMapper.readValue(metrics, MetricsEntity.class);
        service.saveMetrics(metrics);

        var savedMetrics = repository.findAll();

        assertThat(savedMetrics.get(0)).usingRecursiveComparison()
                .ignoringActualNullFields()
                .ignoringExpectedNullFields()
                .isEqualTo(metricsEntity);
    }

    @Test
    @Transactional
    void getAllMetrics() {
        var metricsEntity = random.nextObject(MetricsEntity.class);
        repository.save(metricsEntity);

        var result = service.getAllMetrics();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void saveMetricsThrowException() {
        String metrics = "";

        assertThatThrownBy(() -> service.saveMetrics(metrics)).isInstanceOf(RuntimeException.class);
    }
}
