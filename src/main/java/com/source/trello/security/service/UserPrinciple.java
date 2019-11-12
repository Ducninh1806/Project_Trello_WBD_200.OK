package com.source.trello.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.source.trello.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private static long serialVersionUID = 1L;
    private Long userId;
    private String phoneNumber;
    private String userName;
    private String email;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id, String phone, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = id;
        this.phoneNumber = phone;
        this.userName = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserPrinciple(
                user.getUserId(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUserName(),
                user.getPassword(),
                authorities
        );
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return userId;
    }

    public String getPhone() {
        return phoneNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(userId,user.userId);
    }
}
