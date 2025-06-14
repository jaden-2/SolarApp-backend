package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepo extends JpaRepository<Reports, Integer> {
    List<Reports> findAllByReportRef_reportId(Integer reportId);
}
