package com.nataly.metricsproducer.service;

import com.nataly.metricsproducer.service.impl.MetricsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MetricsServiceImplTest {

    @InjectMocks
    private MetricsServiceImpl service;

    private static final String url = "http://localhost:8081/actuator/health/custom";
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void getMetrics() {
        String data = "data";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(data, HttpStatusCode.valueOf(200));

        when(restTemplate.getForEntity(url, String.class)).thenReturn(responseEntity);

        var result = service.getMetrics();

        verify(kafkaTemplate, atLeastOnce()).send("metrics-topic", data);
        assertThat(result).isEqualTo(data);
    }
}
