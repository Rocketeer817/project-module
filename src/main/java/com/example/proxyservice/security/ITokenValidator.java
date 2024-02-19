package com.example.proxyservice.security;

import java.util.Optional;

public interface ITokenValidator {
    Optional<JwtClaim> validateToken(String token,long userId);
}
