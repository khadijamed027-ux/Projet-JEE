package com.ongconnect.controller;

import com.ongconnect.model.Role;
import com.ongconnect.model.User;
import com.ongconnect.service.UserService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	System.out.println("🚀 RegisterServlet DOPOST appelé");

        User user = new User();
        user.setNom(req.getParameter("nom"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setRole(Role.valueOf(req.getParameter("role")));

        userService.register(user);
        resp.sendRedirect("jsp/auth/login.jsp");
        
        System.out.println("Nom: " + req.getParameter("nom"));
        System.out.println("Email: " + req.getParameter("email"));
        System.out.println("Password: " + req.getParameter("password"));
        System.out.println("Role: " + req.getParameter("role"));
    }
}