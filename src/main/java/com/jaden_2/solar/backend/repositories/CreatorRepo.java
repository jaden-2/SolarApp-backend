package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepo extends JpaRepository<Creator, String> {
}
