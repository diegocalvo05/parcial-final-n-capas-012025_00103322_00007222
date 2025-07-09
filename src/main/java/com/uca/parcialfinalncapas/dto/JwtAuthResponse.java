package com.uca.parcialfinalncapas.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthResponse {
    private String accessToken;

    @Builder.Default
    private String tokenType = "Bearer";
}
