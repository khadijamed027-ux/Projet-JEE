package com.ongconnect.dao;

import com.ongconnect.model.Role;
import com.ongconnect.model.User;

import java.sql.*;

public class UserDAO {

    // 🔹 Inscription
    public void save(User user) {
        String sql = """
            INSERT INTO users (nom, email, password, role, telephone)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, user.getNom());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().name());
            ps.setString(5, user.getTelephone());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Login
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getLong("id"));
                u.setNom(rs.getString("nom"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password")); // IMPORTANT
                u.setTelephone(rs.getString("telephone"));
                u.setRole(Role.valueOf(rs.getString("role")));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTelephone(Long userId, String telephone) {
        String sql = "UPDATE users SET telephone = ? WHERE id = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, telephone);
            ps.setLong(2, userId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
