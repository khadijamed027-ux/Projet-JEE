package com.ongconnect.controller;

import com.ongconnect.model.Role;
import com.ongconnect.model.User;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.UserService;
import com.ongconnect.service.impl.CaseServiceImpl;
import com.ongconnect.service.impl.UserServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nom = req.getParameter("nom");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String telephone = req.getParameter("telephone");
        String role = req.getParameter("role");

        // ===== VALIDATION EMAIL =====
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            req.setAttribute("error", "Email invalide !");
            req.getRequestDispatcher("/views/auth/register.jsp").forward(req, resp);
            return;
        }

        // ===== VALIDATION TELEPHONE =====
        if (!telephone.matches("[234][0-9]{7}")) {
            req.setAttribute("error",
                "Le téléphone doit contenir 8 chiffres et commencer par 2, 3 ou 4");
            req.getRequestDispatcher("/views/auth/register.jsp").forward(req, resp);
            return;
        }

        // ===== CREATION USER =====
        User user = new User();
        user.setNom(nom);
        user.setEmail(email);        // ✅ TU AVAIS OUBLIÉ ÇA
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setRole(Role.valueOf(role));

        userService.register(user);

     // REDIRECTION PROPRE
     resp.sendRedirect(req.getContextPath() + "/views/auth/login.jsp");

    }
}
