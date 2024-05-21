package com.nataly.metricsconsumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nataly.metricsconsumer.MetricsconsumerApplication;
import com.nataly.metricsconsumer.dto.MetricsDto;
import com.nataly.metricsconsumer.service.MetricsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MetricsconsumerApplication.class)
@DirtiesContext
@AutoConfigureMockMvc
public class MetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MetricsService service;

    @Test
    public void getAllMetrics() throws Exception {
        List<MetricsDto> metricsList = List.of(MetricsDto.builder().build());
        when(service.getAllMetrics()).thenReturn(metricsList);

        this.mockMvc.perform(
                        get("/api/v1/metrics")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(metricsList)));
    }

    @Test
    public void getMetricsById() throws Exception {
        MetricsDto metrics = MetricsDto.builder().id(1L).build();
        when(service.getMetricsById(metrics.id())).thenReturn(metrics);

        this.mockMvc.perform(
                        get("/api/v1/metrics/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(metrics)));
    }
}
