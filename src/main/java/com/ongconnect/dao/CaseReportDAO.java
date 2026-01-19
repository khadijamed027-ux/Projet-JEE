package com.ongconnect.dao;

import com.ongconnect.model.CaseReport;
import com.ongconnect.model.CaseStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseReportDAO {

    public void save(CaseReport c) {
        String sql = """
            INSERT INTO case_report (titre, description, localisation, statut, user_id, ong_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getTitre());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getLocalisation());
            ps.setString(4, c.getStatut().name());
            ps.setLong(5, c.getUserId());
            ps.setObject(6, c.getOngId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CaseReport> findAll() {
        List<CaseReport> list = new ArrayList<>();
        String sql = "SELECT * FROM case_report";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                CaseReport c = new CaseReport();
                c.setId(rs.getLong("id"));
                c.setTitre(rs.getString("titre"));
                c.setDescription(rs.getString("description"));
                c.setLocalisation(rs.getString("localisation"));
                c.setStatut(CaseStatus.valueOf(rs.getString("statut")));
                c.setUserId(rs.getLong("user_id"));
                c.setOngId(rs.getLong("ong_id"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}