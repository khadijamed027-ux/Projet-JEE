package com.ongconnect.model;

import java.time.LocalDateTime;

public class CaseReport {

    private Long id;
    private String titre;
    private String description;
    private String localisation;
    private CaseStatus statut;
    private LocalDateTime dateCreation;

    private Long userId;
    private Long ongId;

    public CaseReport() {}

    public CaseReport(Long id, String titre, String description, String localisation,
                      CaseStatus statut, LocalDateTime dateCreation, Long userId, Long ongId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.statut = statut;
        this.dateCreation = dateCreation;
        this.userId = userId;
        this.ongId = ongId;
    }

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

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getOngId() { return ongId; }
    public void setOngId(Long ongId) { this.ongId = ongId; }

    @Override
    public String toString() {
        return "CaseReport{id=" + id + ", titre='" + titre + "', statut=" + statut + "}";
    }
}