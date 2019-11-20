package com.source.trello.message.request;

import javax.validation.constraints.NotBlank;

public class PassForm {

    @NotBlank
    private Long userId;

    @NotBlank
    private String username;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

    public PassForm() {
    }

    public PassForm(@NotBlank Long userId, @NotBlank String username, @NotBlank String oldPassword, @NotBlank String newPassword) {
        this.userId = userId;
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
