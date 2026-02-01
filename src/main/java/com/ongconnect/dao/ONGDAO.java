package com.ongconnect.dao;

import com.ongconnect.model.ONG;
import com.ongconnect.model.StatutValidation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ONGDAO {

    // 🔹 ONG visibles par les donateurs
    public List<ONG> findValidated() {
        List<ONG> list = new ArrayList<>();
        String sql = "SELECT * FROM ong WHERE statut_validation = 'VALIDEE'";

        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ONG o = new ONG();
                o.setId(rs.getLong("id"));
                o.setNom(rs.getString("nom"));
                o.setDescription(rs.getString("description"));
                o.setDomaine(rs.getString("domaine")); // ✅ OK
                o.setUserId(rs.getObject("user_id") != null ? rs.getLong("user_id") : null);
                o.setStatutValidation(
                        StatutValidation.valueOf(rs.getString("statut_validation"))
                );
                list.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔹 ONG connectée
    public ONG findByUserId(Long userId) {
        String sql = "SELECT * FROM ong WHERE user_id = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ONG o = new ONG();
                o.setId(rs.getLong("id"));
                o.setNom(rs.getString("nom"));
                o.setDescription(rs.getString("description"));
                o.setDomaine(rs.getString("domaine"));
                o.setStatutValidation(
                        StatutValidation.valueOf(rs.getString("statut_validation"))
                );
                o.setUserId(rs.getLong("user_id"));
                return o;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void create(ONG ong) {
        String sql = """
            INSERT INTO ong (nom, description, domaine, user_id, statut_validation)
            VALUES (?, ?, ?, ?, ?)
        """;

        System.out.println("=== CREATE ONG ===");
        System.out.println("Nom: " + ong.getNom());
        System.out.println("UserId: " + ong.getUserId());
        System.out.println("Domaine: " + ong.getDomaine());

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, ong.getNom());
            ps.setString(2, ong.getDescription());
            ps.setString(3, ong.getDomaine());
            ps.setLong(4, ong.getUserId());
            ps.setString(5, ong.getStatutValidation().name());

            int rows = ps.executeUpdate();

            System.out.println("Lignes insérées : " + rows);

        } catch (SQLException e) {
            System.out.println("ERREUR INSERT ONG : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateStatut(Long ongId, StatutValidation statut) {
        String sql = "UPDATE ong SET statut_validation = ? WHERE id = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, statut.name());
            ps.setLong(2, ongId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<ONG> findPending() {
        List<ONG> list = new ArrayList<>();
        String sql = "SELECT * FROM ong WHERE statut_validation = 'EN_ATTENTE'";

        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ONG o = new ONG();
                o.setId(rs.getLong("id"));
                o.setNom(rs.getString("nom"));
                o.setDescription(rs.getString("description"));
                o.setDomaine(rs.getString("domaine"));
                o.setUserId(rs.getLong("user_id"));
                o.setStatutValidation(
                    StatutValidation.valueOf(rs.getString("statut_validation"))
                );
                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 /// ✅ STATISTIQUE ADMIN
    public int countONGs() {
        return getInt("SELECT COUNT(*) FROM ong");
    }

    private int getInt(String sql) {
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



}
