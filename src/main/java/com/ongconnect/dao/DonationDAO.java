package com.ongconnect.dao;

import com.ongconnect.model.Donation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonationDAO {

	public boolean save(Donation d) {
	    String sql = "INSERT INTO donation (montant, donor_id, case_id) VALUES (?, ?, ?)";

	    try (Connection c = DBConnection.getConnection();
	         PreparedStatement ps = c.prepareStatement(sql)) {

	        ps.setDouble(1, d.getMontant());
	        if (d.getDonorId() == null) {
	            ps.setNull(2, Types.BIGINT);
	        } else {
	            ps.setLong(2, d.getDonorId());
	        }

	        ps.setLong(3, d.getCaseId());

	        ps.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        throw new RuntimeException("Erreur insertion donation: " + e.getMessage());
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
    public List<Donation> findByCase(Long caseId) {
        List<Donation> list = new ArrayList<>();

        String sql = "SELECT * FROM donation WHERE case_id = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, caseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Donation d = new Donation();
                d.setId(rs.getLong("id"));
                d.setMontant(rs.getDouble("montant"));
                d.setDonorId(rs.getLong("donor_id"));
                d.setCaseId(rs.getLong("case_id"));
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
