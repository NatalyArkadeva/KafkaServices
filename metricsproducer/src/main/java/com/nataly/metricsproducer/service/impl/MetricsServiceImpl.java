package com.nataly.metricsproducer.service.impl;

import com.nataly.metricsproducer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsServiceImpl implements MetricsService {

    private static final String url = "http://localhost:8081/actuator/health/custom";
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public String getMetrics() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        log.info("Sending message: {}", responseEntity.getBody());
        kafkaTemplate.send("metrics-topic", responseEntity.getBody());
        return responseEntity.getBody();
    }
}
