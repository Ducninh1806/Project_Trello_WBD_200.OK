package com.source.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 60)
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

//    @Column(name = "enabled")
//    private boolean enabled;

    @JsonIgnore
    @ManyToMany(mappedBy = "userSet", fetch = FetchType.EAGER)
    private Set<Board> boardSet;

    private String emailId;

    public User(String username, String email, String password, Set<Board> boardSet) {
        this.username = username;
        this.email = email;

        this.password = password;
        this.boardSet = boardSet;
    }

    public User(@NotBlank @Size(min = 2, max = 50) String userName, @NotBlank @Size(max = 60) String email, @NotBlank @Size(min = 3) String password) {
        this.username = userName;
        this.email = email;
        this.password = password;
    }

    public User(@NotBlank @Size(min = 2, max = 50) String username, @NotBlank @Size(max = 60) String email, @NotBlank @Size(min = 3) String password, Set<Role> roles, Set<Board> boardSet) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.boardSet = boardSet;
    }

    public User() {
//        super();
//        this.enabled = false;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Board> getBoardSet() {
        return boardSet;
    }

    public void setBoardSet(Set<Board> boardSet) {
        this.boardSet = boardSet;
    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
}
