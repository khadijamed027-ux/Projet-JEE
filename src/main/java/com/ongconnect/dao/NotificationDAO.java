package com.ongconnect.dao;

import com.ongconnect.model.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public void save(Notification n) {
        String sql = """
            INSERT INTO notification (message, user_id, ong_id, lu)
            VALUES (?, ?, ?, false)
        """;

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, n.getMessage());

            if (n.getUserId() == null)
                ps.setNull(2, Types.BIGINT);
            else
                ps.setLong(2, n.getUserId());

            if (n.getOngId() == null)
                ps.setNull(3, Types.BIGINT);
            else
                ps.setLong(3, n.getOngId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ POUR ADMIN
    public int countUnreadByUser(Long userId) {
        String sql = "SELECT COUNT(*) FROM notification WHERE user_id=? AND lu=false";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ✅ POUR ONG
    public int countUnreadByOng(Long ongId) {
        String sql = "SELECT COUNT(*) FROM notification WHERE ong_id=? AND lu=false";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, ongId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Notification> findByUser(Long userId) {

        List<Notification> list = new ArrayList<>();

        String sql = "SELECT * FROM notification WHERE user_id=? ORDER BY date_envoi DESC";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ ONG
    public List<Notification> findByOng(Long ongId) {

        List<Notification> list = new ArrayList<>();

        String sql = "SELECT * FROM notification WHERE ong_id=? ORDER BY date_envoi DESC";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, ongId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Notification map(ResultSet rs) throws SQLException {

        Notification n = new Notification();

        n.setId(rs.getLong("id"));
        n.setMessage(rs.getString("message"));
        n.setLu(rs.getBoolean("lu"));

        n.setUserId(rs.getObject("user_id") != null ?
                    rs.getLong("user_id") : null);

        n.setOngId(rs.getObject("ong_id") != null ?
                   rs.getLong("ong_id") : null);

        return n;
    }
 // ✅ MARQUER COMME LUES LES NOTIFICATIONS D’UN USER (ADMIN)
    public void markReadByUser(Long userId) {

        String sql = "UPDATE notification SET lu = true WHERE user_id = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ MARQUER COMME LUES LES NOTIFICATIONS D’UNE ONG
    public void markReadByOng(Long ongId) {

        String sql = "UPDATE notification SET lu = true WHERE ong_id = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, ongId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
