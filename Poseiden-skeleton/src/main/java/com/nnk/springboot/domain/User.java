package com.nnk.springboot.domain;

import com.nnk.springboot.domain.validators.password.ValidPassword;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", unique=true, nullable=false)
    private Long id;
    @NotBlank(message = "Username is mandatory")
    @Column(name="USERNAME", nullable=false, length=125)
    private String username;
    @NotBlank(message = "Password is mandatory")
    @ValidPassword
    @Column(name="PASSWORD", nullable=false, length=125)
    private String password;
    @NotBlank(message = "FullName is mandatory")
    @Column(name="FULLNAME", nullable=false, length=125)
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    @Column(name="ROLE", nullable=false, length=125)
    private String role;

    private String email_verified;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User(Long id, String username, String password, String fullname, String role, String email_verified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.email_verified = email_verified;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(String email_verified) {
        this.email_verified = email_verified;
    }
}
