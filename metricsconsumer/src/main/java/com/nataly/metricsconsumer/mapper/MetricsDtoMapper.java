package com.nataly.metricsconsumer.mapper;

import com.nataly.metricsconsumer.dto.DetailsDto;
import com.nataly.metricsconsumer.dto.MetricsDto;
import com.nataly.metricsconsumer.model.DetailsEntity;
import com.nataly.metricsconsumer.model.MetricsEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.ERROR
)
public interface MetricsDtoMapper {

    @Mapping(target = "pingStatus", source = "components.ping.status")
    @Mapping(target = "details", source = "components.diskSpace.details")
    MetricsDto toMetricsDto(MetricsEntity entity);

    List<MetricsDto> toListMetricsDto(List<MetricsEntity> entity);

    @BeanMapping(ignoreUnmappedSourceProperties = "id")
    DetailsDto toDetailsDto(DetailsEntity entity);
}
