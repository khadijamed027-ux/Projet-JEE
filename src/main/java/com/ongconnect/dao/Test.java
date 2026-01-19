package com.ongconnect.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class Test{

    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("✅ Connexion MySQL réussie !");
            conn.close();
        } catch (SQLException e) {
            System.err.println("❌ Connexion échouée");
            e.printStackTrace();
        }
    }
}