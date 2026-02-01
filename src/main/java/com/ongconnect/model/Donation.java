package com.ongconnect.model;

import java.time.LocalDateTime;

public class Donation {

    private Long id;
    private double montant;
    private Long donorId;
    private Long caseId;
    private LocalDateTime dateDonation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Long getDonorId() {
		return donorId;
	}
	public void setDonorId(Long donorId) {
		this.donorId = donorId;
	}
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public LocalDateTime getDateDonation() {
		return dateDonation;
	}
	public void setDateDonation(LocalDateTime dateDonation) {
		this.dateDonation = dateDonation;
	}

   }
