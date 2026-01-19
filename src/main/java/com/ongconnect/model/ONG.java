package com.ongconnect.model;

import java.time.LocalDateTime;

public class ONG {

    private Long id;
    private String nom;
    private String description;
    private StatutValidation statutValidation;
    private LocalDateTime dateCreation;

    public ONG() {}

    public ONG(Long id, String nom, String description, StatutValidation statutValidation, LocalDateTime dateCreation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.statutValidation = statutValidation;
        this.dateCreation = dateCreation;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public StatutValidation getStatutValidation() { return statutValidation; }
    public void setStatutValidation(StatutValidation statutValidation) { this.statutValidation = statutValidation; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    @Override
    public String toString() {
        return "ONG{id=" + id + ", nom='" + nom + "', statut=" + statutValidation + "}";
    }
}