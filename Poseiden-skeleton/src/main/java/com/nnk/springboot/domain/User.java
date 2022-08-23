package com.nnk.springboot.domain;

import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User { //implements Validator {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", unique=true, nullable=false)
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    @Column(name="USERNAME", nullable=false, length=125)
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Column(name="PASSWORD", nullable=false, length=125)
    private String password;
    @NotBlank(message = "FullName is mandatory")
    @Column(name="FULLNAME", nullable=false, length=125)
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    @Column(name="ROLE", nullable=false, length=125)
    private String role;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Integer id, String username, String password, String fullname, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
