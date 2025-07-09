package com.uca.parcialfinalncapas.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String correo;
    private String password;
}
