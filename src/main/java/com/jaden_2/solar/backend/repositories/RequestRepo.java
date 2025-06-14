package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.EstimationRequestId;
import com.jaden_2.solar.backend.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request, EstimationRequestId> {
}
