package com.nataly.metricsconsumer.service.impl;

import com.nataly.metricsconsumer.service.MetricsListener;
import com.nataly.metricsconsumer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsListenerImpl implements MetricsListener {

    private final MetricsService service;

    @Override
    @KafkaListener(id = "metricsGroup", topics = "metrics-topic")
    public void listen(String metrics) {
        log.info("Received from metricsTopic: {}", metrics);
        service.saveMetrics(metrics);
        log.info("Metrics saved");
    }

    @Override
    @KafkaListener(id = "dltGroup", topics = "metricsTopic.DLT")
    public void dltListen(byte[] in) {
        log.info("Received from DLT: {}", new String(in));
    }
}
