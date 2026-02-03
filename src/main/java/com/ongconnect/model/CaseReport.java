package com.ongconnect.model;

import java.time.LocalDateTime;

public class CaseReport {

    private Long id;
    private String titre;
    private String description;
    private String localisation;

    private CaseStatus statut;
    private TypeCase typeCase;
    private NiveauUrgence niveauUrgence;

    private LocalDateTime dateCreation;
    private double objectif;
    private double totalDons;

    private Long ongId;
    

    // GETTERS SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    public CaseStatus getStatut() { return statut; }
    public void setStatut(CaseStatus statut) { this.statut = statut; }

    public TypeCase getTypeCase() { return typeCase; }
    public void setTypeCase(TypeCase typeCase) { this.typeCase = typeCase; }

    public NiveauUrgence getNiveauUrgence() { return niveauUrgence; }
    public void setNiveauUrgence(NiveauUrgence niveauUrgence) {
        this.niveauUrgence = niveauUrgence;
    }

    public Long getOngId() { return ongId; }
    public void setOngId(Long ongId) { this.ongId = ongId; }
 // 🔴 NOUVEAU
    

    public double getObjectif() {
        return objectif;
    }

    public void setObjectif(double objectif) {
        this.objectif = objectif;
    }

    public double getTotalDons() {
        return totalDons;
    }

    public void setTotalDons(double totalDons) {
        this.totalDons = totalDons;
    }

    public boolean isBloque() {
        return totalDons >= objectif;
    }
    

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

}
