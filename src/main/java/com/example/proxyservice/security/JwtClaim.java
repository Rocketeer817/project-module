package com.example.proxyservice.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtClaim {
    private Long userId;
    private String email;
    private List<Role> roles = new ArrayList<>();
    private Date createdAt;
    private Date expiresAt;
}
