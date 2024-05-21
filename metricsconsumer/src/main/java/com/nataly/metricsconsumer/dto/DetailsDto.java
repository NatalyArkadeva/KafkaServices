package com.nataly.metricsconsumer.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record DetailsDto(
        String total,
        String free,
        String threshold,
        String path,
        boolean exists
) {
}
