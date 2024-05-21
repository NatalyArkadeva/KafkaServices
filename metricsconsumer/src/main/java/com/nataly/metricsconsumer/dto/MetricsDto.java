package com.nataly.metricsconsumer.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record MetricsDto(
        Long id,
        String status,
        DetailsDto details,
        String pingStatus
) {
}
