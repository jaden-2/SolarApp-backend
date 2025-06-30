package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.entities.CreatorToken;
import com.jaden_2.solar.backend.repositories.CreatorTokenRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class CreatorTokenService {
    private final CreatorTokenRepo repo;
    CreatorTokenService(CreatorTokenRepo repo){
        this.repo = repo;
    }

    public void createToken(String tokenId, Creator creator, long expiration){
        CreatorToken token = CreatorToken.builder()
                .createdAt(ZonedDateTime.now(Clock.systemUTC()))
                .expiresAt(ZonedDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()+expiration), ZoneId.of("UTC")))
                .creator(creator)
                .tokenId(tokenId)
                .isUsed(false)
                .isRevoked(false)
                .build();
        System.out.println("Created token in db");
        repo.save(token);
    }

    public boolean isTokenValid(String tokenId){
        CreatorToken token = repo.findByTokenId(tokenId).orElseThrow(()-> new EntityNotFoundException("Token does not exist"));
        if(!token.getIsUsed() && !token.getIsRevoked()){
            if(token.getExpiresAt().isAfter(ZonedDateTime.now(Clock.systemUTC()))) {
                token.setIsUsed(true);
                repo.save(token);
                return true;
            }else return false; // the token has expired
        }
       revokeTokens(token.getCreator());
        return false;
    }
    public void revokeTokens(Creator creator){
        List<CreatorToken> tokens = repo.findAllByCreatorAndIsUsed(creator, false);
        tokens.forEach(CreatorToken::revoke);
        repo.saveAll(tokens);
    }
}
