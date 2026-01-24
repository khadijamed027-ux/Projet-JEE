package com.ongconnect.dao;

import com.ongconnect.model.CaseReport;
import com.ongconnect.model.CaseStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseReportDAO {
	public void save(CaseReport c) {
	    String sql = "INSERT INTO case_report (titre, description, localisation, statut, user_id, ong_id) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setString(1, c.getTitre());
	        ps.setString(2, c.getDescription());
	        ps.setString(3, c.getLocalisation());
	        ps.setString(4, c.getStatut() != null ? c.getStatut().name() : "SIGNALE"); // par défaut SIGNALE
	        ps.setLong(5, c.getUserId());

	        if(c.getOngId() != null) {
	            ps.setLong(6, c.getOngId());
	        } else {
	            ps.setNull(6, java.sql.Types.BIGINT);
	        }

	        ps.executeUpdate();

	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (rs.next()) {
	                c.setId(rs.getLong(1)); // récupère l'ID auto-incrémenté
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public List<CaseReport> findCasesToAssign() {
	    List<CaseReport> list = new ArrayList<>();
	    String sql = "SELECT * FROM case_report WHERE ong_id IS NULL AND statut = 'SIGNALE'";

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
	            c.setOngId(null);
	            list.add(c);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

    public void assignCaseToOng(long caseId, long ongId) {
        String sql = "UPDATE case_report SET ong_id = ?, statut = 'ACCEPTE' WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, ongId);
            ps.setLong(2, caseId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CaseReport> findCasesForOng(long ongId) {
        List<CaseReport> list = new ArrayList<>();
        String sql = "SELECT * FROM case_report WHERE ong_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, ongId);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}