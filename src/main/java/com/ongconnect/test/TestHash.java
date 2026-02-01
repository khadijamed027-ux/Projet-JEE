package com.ongconnect.test;

import com.ongconnect.util.PasswordUtil;

public class TestHash {
    public static void main(String[] args) {
        System.out.println(
            PasswordUtil.hashPassword("admin123")
        );
    }
}
