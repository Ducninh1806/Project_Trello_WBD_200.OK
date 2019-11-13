package com.source.trello.message.request;

import com.source.trello.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpForm {

    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<Role> role;

    @NotBlank
    @Size(min = 3)
    private String password;

    public SignUpForm() {
    }

    public SignUpForm(@NotBlank @Size(min = 2, max = 50) String username, @NotBlank @Size(max = 60) @Email String email, Set<Role> role, @NotBlank @Size(min = 3) String password) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
