package com.ongconnect.dao;

import com.ongconnect.model.ONG;
import com.ongconnect.model.StatutValidation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ONGDAO {

    public void save(ONG ong) {
        String sql = "INSERT INTO ong (nom, description, statut_validation) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ong.getNom());
            ps.setString(2, ong.getDescription());
            ps.setString(3, ong.getStatutValidation().name());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ONG> findAll() {
        List<ONG> list = new ArrayList<>();
        String sql = "SELECT * FROM ong";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ONG ong = new ONG();
                ong.setId(rs.getLong("id"));
                ong.setNom(rs.getString("nom"));
                ong.setDescription(rs.getString("description"));
                ong.setStatutValidation(
                        StatutValidation.valueOf(rs.getString("statut_validation"))
                );
                list.add(ong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}