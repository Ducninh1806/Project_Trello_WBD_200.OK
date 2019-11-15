package com.source.trello.message.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private Long userId;
    private String email;
    private String token;
   // private String type = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> roles;

//    public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> roles) {
//        this.token = accessToken;
//        this.username = username;
//        this.roles = roles;
//    }

    public JwtResponse( String token, Long id, String email, String username, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.userId = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

//    public String getTokenType() {
//        return type;
//    }
//
//    public void setTokenType(String tokenType) {
//        this.type = tokenType;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }
}
