package com.jaden_2.solar.backend.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatorRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 8, message = "password must be at least 8 characters")
    private String password;
    private String role;
}
