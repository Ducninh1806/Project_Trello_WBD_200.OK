package com.source.trello.message.request;

import com.source.trello.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpForm {
    @NotBlank
    @Size(min = 3,max = 50)
    private String phoneNumber;
    @NotBlank
    @Size(min = 2,max = 50)
    private String userName;

    @NotBlank
    @Size(max = 60)
    private String email;

    private Set<Role> role;

    @NotBlank
    @Size(min = 3)
    private String password;

    public SignUpForm() {
    }

    public SignUpForm(@Size(min = 3, max = 50) String phoneNumber, @Size(min = 2, max = 50) String userName, @NotBlank @Size(max = 60)  String email, Set<Role> role, @NotBlank @Size(min = 3) String password) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
