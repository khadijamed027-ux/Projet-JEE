package com.ongconnect.dao;

import com.ongconnect.model.Donation;
import java.sql.*;

public class DonationDAO {

    public void save(Donation d) {
        String sql = """
            INSERT INTO donation (montant, donor_id, case_id)
            VALUES (?, ?, ?)
        """;

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, d.getMontant());
            ps.setLong(2, d.getDonorId());
            ps.setLong(3, d.getCaseId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ STATISTIQUE ADMIN
    public int countDonations() {
        return getInt("SELECT COUNT(*) FROM donation");
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
