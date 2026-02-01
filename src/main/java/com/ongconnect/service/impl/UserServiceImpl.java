package com.ongconnect.service.impl;

import com.ongconnect.dao.UserDAO;
import com.ongconnect.model.User;
import com.ongconnect.service.UserService;
import com.ongconnect.util.PasswordUtil;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAO();

    @Override
    public User login(String email, String password) {
        User user = userDAO.findByEmail(email);

        if (user == null) return null;

        String hashedInput = PasswordUtil.hashPassword(password);

        if (user.getPassword().equals(hashedInput)) {
            return user;
        }

        return null;
    }

    @Override
    public void register(User user) {
        // 🔐 hash AVANT sauvegarde
        user.setPassword(
            PasswordUtil.hashPassword(user.getPassword())
        );
        userDAO.save(user);
    }
}
