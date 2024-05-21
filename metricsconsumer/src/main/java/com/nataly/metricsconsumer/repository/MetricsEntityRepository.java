package com.nataly.metricsconsumer.repository;

import com.nataly.metricsconsumer.model.MetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricsEntityRepository extends JpaRepository<MetricsEntity, Long> {
}
