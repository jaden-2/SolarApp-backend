package com.jaden_2.solar.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class CreatorToken {
    @Id
    private String tokenId;
    @ManyToOne
    @JoinColumn(referencedColumnName ="username", nullable = false)
    private Creator creator;

    @Setter
    private Boolean isUsed;
    @Setter
    private Boolean isRevoked;
    private ZonedDateTime createdAt;
    private ZonedDateTime expiresAt;

    public void revoke(){
        isRevoked= true;
    }
}
