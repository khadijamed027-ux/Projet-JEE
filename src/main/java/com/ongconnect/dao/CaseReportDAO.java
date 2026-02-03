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
            (titre, description, localisation, statut, type_case, niveau_urgence, ong_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getTitre());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getLocalisation());
            ps.setString(4, c.getStatut().name());
            ps.setString(5, c.getTypeCase().name());
            ps.setString(6, c.getNiveauUrgence().name());
            ps.setLong(7, c.getOngId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CaseReport map(ResultSet rs) throws SQLException {

        CaseReport c = new CaseReport();

        c.setId(rs.getLong("id"));
        c.setTitre(rs.getString("titre"));
        c.setDescription(rs.getString("description"));
        c.setLocalisation(rs.getString("localisation"));

        c.setStatut(CaseStatus.valueOf(rs.getString("statut")));
        c.setTypeCase(TypeCase.valueOf(rs.getString("type_case")));
        c.setNiveauUrgence(
            NiveauUrgence.valueOf(rs.getString("niveau_urgence"))
        );

        c.setOngId(rs.getLong("ong_id"));

        // ✅ ICI ÉTAIT LE BUG PRINCIPAL
        c.setObjectif(rs.getDouble("objectif"));

        // ✅ Calcul total des dons
        String sql = "SELECT COALESCE(SUM(montant),0) FROM donation WHERE case_id=?";
        try (PreparedStatement ps =
             DBConnection.getConnection().prepareStatement(sql)) {

            ps.setLong(1, c.getId());
            ResultSet r = ps.executeQuery();

            if (r.next()) {
                c.setTotalDons(r.getDouble(1));
            }
        }

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
    public List<CaseReport> findAllForAdmin() {
        List<CaseReport> list = new ArrayList<>();

        String sql = "SELECT * FROM case_report ORDER BY id DESC";

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
    public void update(CaseReport c) {

        String sql = """
            UPDATE case_report
            SET titre=?,
                description=?,
                localisation=?,
                objectif=?
            WHERE id=?
        """;

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getTitre());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getLocalisation());
            ps.setDouble(4, c.getObjectif());
            ps.setLong(5, c.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(Long id) {

        String sql = "DELETE FROM case_report WHERE id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int countByUrgence(String niveau) {

        String sql = "SELECT COUNT(*) FROM case_report WHERE niveau_urgence = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, niveau);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    
}
