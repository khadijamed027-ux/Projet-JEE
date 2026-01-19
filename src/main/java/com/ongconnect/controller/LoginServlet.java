package com.ongconnect.controller;

import com.ongconnect.model.User;
import com.ongconnect.service.UserService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        System.out.println("email: "+ email);
        System.out.println("password: "+ password);

        User user = userService.login(email, password);
        System.out.println("user: "+ user);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            switch (user.getRole().name()) {
                case "ADMIN":
                    resp.sendRedirect("jsp/admin/admin-dashboard.jsp");
                    break;
                case "ONG":
                    resp.sendRedirect("jsp/ong/ong-dashboard.jsp");
                    break;
                default:
                    resp.sendRedirect("jsp/user/dashboard.jsp");
            }
        } else {
            req.setAttribute("error", "Email ou mot de passe incorrect");
            req.getRequestDispatcher("jsp/auth/login.jsp").forward(req, resp);
        }
    }
}