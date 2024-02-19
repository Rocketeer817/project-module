package com.example.proxyservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenValidator implements ITokenValidator {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    public Optional<JwtClaim> validateToken(String token,long userId) {
        ValidateRequestDto validateRequestDto = new ValidateRequestDto();
        validateRequestDto.setToken(token);
        validateRequestDto.setUserId(userId);
        JwtClaim jwtClaim= restTemplateBuilder.build().postForObject("http://localhost:8081/auth/validate",validateRequestDto, JwtClaim.class);

        if(jwtClaim != null){
            return Optional.of(jwtClaim);
        }

        return Optional.empty();
    }
}
