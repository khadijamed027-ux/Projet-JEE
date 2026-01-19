package com.ongconnect.dao;

import com.ongconnect.model.User;
import com.ongconnect.model.Role;

import java.sql.*;

public class UserDAO {

    // Enregistrement utilisateur
	public void save(User user) {
	    String sql = "INSERT INTO users (nom, email, password, role) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, user.getNom());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getPassword());
	        ps.setString(4, user.getRole().name());

	        int rows = ps.executeUpdate(); // ✅ CORRECT

	        if (rows > 0) {
	            System.out.println("✅ Utilisateur inséré avec succès");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    // Authentification
    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        User user = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setNom(rs.getString("nom"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}