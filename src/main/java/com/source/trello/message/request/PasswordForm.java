package com.source.trello.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordForm {

    private Long id;

    @NotBlank
    @Size(min = 2,max = 50)
    private String username;

    @NotBlank
    @Size(min = 3)
    private String currentPassword;

    @NotBlank
    @Size(min = 3)
    private String newPassword;

    public PasswordForm() {
    }

    public PasswordForm(Long id, @NotBlank @Size(min = 2, max = 50) String username, @NotBlank @Size(min = 3) String currentPassword, @NotBlank @Size(min = 3) String newPassword) {
        this.id = id;
        this.username = username;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
