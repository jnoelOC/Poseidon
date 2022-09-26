package com.nnk.springboot.domain;

import com.nnk.springboot.domain.validators.password.ValidPassword;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", unique=true, nullable=false)
    private Long id;
    @NotBlank(message = "Username est obligatoire")
    @Size(min=3, max=125)
    @Column(name="USERNAME", nullable=false, length=125)
    private String username;
    @NotBlank(message = "Password est obligatoire")
    @ValidPassword
    @Column(name="PASSWORD", nullable=false, length=125)
    private String password;
    @NotBlank(message = "FullName est obligatoire")
    @Size(min=3, max=125)
    @Column(name="FULLNAME", nullable=false, length=125)
    private String fullname;
    @NotBlank(message = "Role est obligatoire")
    @Column(name="ROLE", nullable=false, length=125)
    private String role;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User(Long id, String username, String password, String fullname, String role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
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

}
