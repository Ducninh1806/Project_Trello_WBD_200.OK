package com.source.trello.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotBlank
    @Size(min = 3,max = 50)
    private String userName;

    @NotBlank
    @Size(min = 4,max = 100)
    private String password;

    public LoginForm() {
    }

    public LoginForm(@NotBlank @Size(min = 3, max = 50) String userName, @NotBlank @Size(min = 4, max = 100) String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
