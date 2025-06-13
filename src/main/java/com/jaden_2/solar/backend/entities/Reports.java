package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.entities.converters.ReportTypeConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(schema = "solar_inventory", name = "reports")
@Data
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "reportId", name = "systemReport", nullable = false)
    private SystemReport reportId;
    @Convert(converter = ReportTypeConverter.class)
    private SystemReport report;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", nullable = false)
    private Creator creator;

    private LocalDateTime createdAt;

    @PrePersist
    private void setCreatedAt(){
        setCreatedAt(LocalDateTime.now());
    }

    public Reports(SystemReport systemReport){
        setReport(systemReport);
        setReportId(systemReport);
        setCreator(systemReport.getCreator());
    }
}
