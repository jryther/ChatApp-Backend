package com.joshryther.chatappbackend.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwt;

    public AuthenticationResponse(String token) {
        this.jwt = token;
    }

    public String getJwt() {
        return this.jwt;
    }
}