package com.ongconnect.model;

import java.time.LocalDateTime;

public class CaseReport {

    private Long id;
    private String titre;
    private String description;
    private String localisation;
    private CaseStatus statut;
    private LocalDateTime dateCreation;
    private TypeCase typeCase;

    // ✅ LE CAS APPARTIENT À UNE ONG
    private Long ongId;

    public CaseReport() {}

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

    public TypeCase getTypeCase() { return typeCase; }
    public void setTypeCase(TypeCase typeCase) { this.typeCase = typeCase; }

    public Long getOngId() { return ongId; }
    public void setOngId(Long ongId) { this.ongId = ongId; }
}
