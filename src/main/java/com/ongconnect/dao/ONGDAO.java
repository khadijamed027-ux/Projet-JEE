package com.ongconnect.dao;

import com.ongconnect.model.ONG;
import com.ongconnect.model.StatutValidation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ONGDAO {

    public List<ONG> findValidatedOngs() {
        List<ONG> list = new ArrayList<>();
        String sql = "SELECT * FROM ong WHERE statut_validation='VALIDEE'";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ONG o = new ONG();
                o.setId(rs.getLong("id"));
                o.setNom(rs.getString("nom"));
                o.setDescription(rs.getString("description"));
                o.setStatutValidation(StatutValidation.valueOf(rs.getString("statut_validation")));
                o.setUserId(rs.getLong("user_id"));
                list.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ONG findByUserId(Long userId) {
        String sql = "SELECT * FROM ong WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ONG o = new ONG();
                o.setId(rs.getLong("id"));
                o.setNom(rs.getString("nom"));
                o.setDescription(rs.getString("description"));
                o.setStatutValidation(StatutValidation.valueOf(rs.getString("statut_validation")));
                o.setUserId(rs.getLong("user_id"));
                return o;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<ONG> findValidatedWithUser() {
        List<ONG> list = new ArrayList<>();
        String sql = "SELECT * FROM ong WHERE statut_validation = 'VALIDEE' AND user_id IS NOT NULL";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ONG ong = new ONG();
                ong.setId(rs.getLong("id"));
                ong.setNom(rs.getString("nom"));
                ong.setUserId(rs.getLong("user_id"));
                list.add(ong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<ONG> findAll() {
        List<ONG> list = new ArrayList<>();
        String sql = "SELECT * FROM ong ";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ONG ong = new ONG();
                ong.setId(rs.getLong("id"));
                ong.setNom(rs.getString("nom"));
                ong.setDescription(rs.getString("description"));

                // Conversion String -> Enum
                ong.setStatutValidation(
                    StatutValidation.valueOf(rs.getString("statut_validation"))
                );

                // user_id peut être null, on utilise Long
                ong.setUserId(rs.getObject("user_id") != null ? rs.getLong("user_id") : null);

                list.add(ong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}