package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.SystemReport;
import com.jaden_2.solar.backend.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemReportsRepo extends JpaRepository<SystemReport, Integer> {
    public List<SystemReport> findByCreator(Creator creator);
}
