package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.Request;
import com.jaden_2.solar.backend.entities.SystemReport;
import com.jaden_2.solar.backend.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SystemReportsRepo extends JpaRepository<SystemReport, Integer> {
    public List<SystemReport> findAllByCreator(Creator creator);

    Optional<SystemReport> findByRequest(Request request);
}
