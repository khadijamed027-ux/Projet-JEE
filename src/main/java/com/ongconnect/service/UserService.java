package com.ongconnect.service;

import com.ongconnect.model.User;

public interface UserService {

    User login(String email, String password);

    void register(User user);
    
}
