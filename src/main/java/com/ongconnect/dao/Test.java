package com.ongconnect.dao;

import java.sql.Connection;
import java.sql.SQLException;



import java.sql.Connection;

public class Test{
    public static void main(String[] args) {
        Connection c = DBConnection.getConnection();

        if (c != null) {
            System.out.println("✅ CONNEXION OK");
        } else {
            System.out.println("❌ CONNEXION ECHOUEE");
        }
    }
}
