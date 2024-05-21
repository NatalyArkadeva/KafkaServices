package com.nataly.metricsconsumer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "details")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private String total;

    @Column(name = "free")
    private String free;

    @Column(name = "threshold")
    private String threshold;

    @Column(name = "path")
    private String path;

    @Column(name = "exists")
    private boolean exists;
}
