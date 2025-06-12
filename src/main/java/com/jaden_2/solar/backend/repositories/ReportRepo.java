package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<Reports, Integer> {
}
