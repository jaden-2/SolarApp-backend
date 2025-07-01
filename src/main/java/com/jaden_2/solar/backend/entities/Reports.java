package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.entities.converters.ReportTypeConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


import java.time.ZoneId;
import java.time.ZonedDateTime;


@Entity
@Table(name = "reports")
@Data
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "reportId", name = "system_report_id", nullable = false)
    private SystemReport reportRef;

    @Convert(converter = ReportTypeConverter.class)
    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private SystemReport report;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", nullable = false)
    private Creator creator;

    private ZonedDateTime createdAt;

    @PrePersist
    private void setCreatedAt(){
        setCreatedAt(ZonedDateTime.now(ZoneId.of("Etc/UTC")));
    }

    public Reports(SystemReport systemReport){
        setReport(systemReport);
        setReportRef(systemReport);
        setCreator(systemReport.getCreator());
    }

}
