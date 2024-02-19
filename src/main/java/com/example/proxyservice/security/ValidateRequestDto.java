package com.example.proxyservice.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateRequestDto {
    private Long userId;
    private String token;
}
