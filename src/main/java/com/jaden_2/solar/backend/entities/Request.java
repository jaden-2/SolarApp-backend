package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.DTOs.EstimatorRequest;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Request {
    @EmbeddedId
    private EstimationRequestId requestId;

    public Request( EstimatorRequest request){
        setRequestId(new EstimationRequestId(request));
    }
}
