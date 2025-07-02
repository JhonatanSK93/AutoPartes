package com.tecser.autopartes.security;

public class AuthResponse {
    private String token;
    private Long rolId;

    public AuthResponse(String token, Long rolId) {
        this.token = token;
        this.rolId = rolId;
    }

    public String getToken() {
        return token;
    }

    public Long getRol() {
        return rolId;
    }
}
