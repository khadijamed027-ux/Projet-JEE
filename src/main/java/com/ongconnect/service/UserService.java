package com.ongconnect.service;

import com.ongconnect.dao.UserDAO;
import com.ongconnect.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User login(String email, String password) {
    	System.out.println("email: "+email+" password: "+password);
        return userDAO.findByEmailAndPassword(email, password);
    }

    public void register(User user) {
        userDAO.save(user);
    }
}