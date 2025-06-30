package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.entities.CreatorToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreatorTokenRepo extends JpaRepository<CreatorToken, String> {
    public Optional<CreatorToken> findByTokenId(String tokenId);
    public List<CreatorToken> findAllByCreatorAndIsUsed(Creator creator, boolean isRevoked);
}
