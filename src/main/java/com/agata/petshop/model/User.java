package com.agata.petshop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user")
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username", unique = true)
    @NotEmpty
    @NotNull
    private String username;

    @Column(name = "role")
    @NotEmpty
    @NotNull
    private String role;

    @Column(name = "password")
    @NotEmpty
    @NotNull
    private String password;

    @NotEmpty
    @NotNull
    private String passwordConfirm;

    @Column(name = "email", unique = true)
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Column(name = "phone_number")
    private int phoneNumber;

    public User(String username, String role, String password, String passwordConfirm, String email, int phoneNumber) {
        this.username = username;
        this.role = role;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User() {

    }
}
