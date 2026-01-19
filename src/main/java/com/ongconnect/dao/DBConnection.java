package com.ongconnect.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/ProjetJEE?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion MySQL OK");
            return c;
        } catch (Exception e) {
            System.out.println("❌ Connexion MySQL échouée");
            e.printStackTrace();
            return null;
        }
    }
}