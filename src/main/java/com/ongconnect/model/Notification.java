package com.ongconnect.model;

import java.time.LocalDateTime;

public class Notification {

    private Long id;
    private String message;
    private boolean lu;
    private LocalDateTime dateCreation;
    private Long userId;
    private Long ongId;

    public Notification() {}

    public Notification(Long id, String message, boolean lu,
                        LocalDateTime dateCreation, Long userId, Long ongId) {
        this.id = id;
        this.message = message;
        this.lu = lu;
        this.dateCreation = dateCreation;
        this.userId = userId;
        this.ongId = ongId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isLu() { return lu; }
    public void setLu(boolean lu) { this.lu = lu; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getOngId() { return ongId; }
    public void setOngId(Long ongId) { this.ongId = ongId; }

    @Override
    public String toString() {
        return "Notification{id=" + id + ", message='" + message + "'}";
    }
}