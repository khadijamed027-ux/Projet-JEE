package com.ongconnect.dao;

import com.ongconnect.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseReportDAO {

    /* ==============================
       CAS PUBLICS (ADMIN + DONATEUR)
       ============================== */
    public List<CaseReport> findPublicCases() {
        List<CaseReport> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM case_report
            WHERE statut IN ('EN_COURS', 'RESOLU')

        """;

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ==============================
       CAS PUBLICS D’UNE ONG
       ============================== */
    public List<CaseReport> findPublicCasesByOng(Long ongId) {
        List<CaseReport> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM case_report
            WHERE ong_id = ?
              AND statut IN ('EN_COURS', 'RESOLU')

        """;

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, ongId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ==============================
       DASHBOARD ONG
       ============================== */
    public List<CaseReport> findCasesForOng(Long ongId) {
        List<CaseReport> list = new ArrayList<>();

        String sql = "SELECT * FROM case_report WHERE ong_id = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, ongId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ==============================
       CRÉATION PAR ONG UNIQUEMENT
       ============================== */
    public void create(CaseReport c) {
        String sql = """
            INSERT INTO case_report
            (titre, description, localisation, statut, type_case, ong_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getTitre());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getLocalisation());
            ps.setString(4, c.getStatut().name());
            ps.setString(5, c.getTypeCase().name());
            ps.setLong(6, c.getOngId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ==============================
       MAPPER
       ============================== */
    private CaseReport map(ResultSet rs) throws SQLException {
        CaseReport c = new CaseReport();
        c.setId(rs.getLong("id"));
        c.setTitre(rs.getString("titre"));
        c.setDescription(rs.getString("description"));
        c.setLocalisation(rs.getString("localisation"));
        c.setStatut(CaseStatus.valueOf(rs.getString("statut")));
        c.setTypeCase(TypeCase.valueOf(rs.getString("type_case")));
        c.setOngId(rs.getLong("ong_id"));
        return c;
    }
    public CaseReport findById(Long id) {
        String sql = "SELECT * FROM case_report WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 // ✅ STATISTIQUE ADMIN
    public int countCases() {
        return getInt("SELECT COUNT(*) FROM case_report");
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
