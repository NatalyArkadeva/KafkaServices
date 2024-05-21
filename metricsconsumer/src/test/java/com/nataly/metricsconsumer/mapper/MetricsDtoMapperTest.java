package com.nataly.metricsconsumer.mapper;

import com.nataly.metricsconsumer.model.MetricsEntity;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class MetricsDtoMapperTest {

    private final MetricsDtoMapper mapper = Mappers.getMapper(MetricsDtoMapper.class);
    private final EasyRandom random = new EasyRandom();

    @Test
    void toMetricsDto() {
        var metricsEntity = random.nextObject(MetricsEntity.class);

        var result = mapper.toMetricsDto(metricsEntity);

        assertThat(result).usingRecursiveComparison()
                .ignoringFields("pingStatus", "details")
                .isEqualTo(metricsEntity);
        assertThat(result.details()).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(metricsEntity.getComponents().getDiskSpace().getDetails());
        assertThat(result.pingStatus()).isEqualTo(metricsEntity.getComponents().getPing().getStatus());
    }
}
