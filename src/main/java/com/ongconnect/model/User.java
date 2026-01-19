package com.ongconnect.model;

import java.time.LocalDateTime;

public class User {

    private Long id;
    private String nom;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime dateCreation;

    public User() {
    }

    public User(Long id, String nom, String email, String password, Role role, LocalDateTime dateCreation) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dateCreation = dateCreation;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    @Override
    public String toString() {
        return "User{id=" + id + ", nom='" + nom + "', email='" + email + "', role=" + role + "}";
    }
}