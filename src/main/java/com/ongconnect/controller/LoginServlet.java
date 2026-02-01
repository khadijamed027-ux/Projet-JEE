package com.ongconnect.controller;

import com.ongconnect.model.User;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.UserService;
import com.ongconnect.service.impl.CaseServiceImpl;
import com.ongconnect.service.impl.UserServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	

	private UserService userService = new UserServiceImpl();

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

            switch (user.getRole()) {
            case ADMIN:
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
                break;

            case ONG:
                resp.sendRedirect(req.getContextPath() + "/public/ong");
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/views/user/dashboard.jsp");
        }

        } else {
            req.setAttribute("error", "Email ou mot de passe incorrect");
            req.getRequestDispatcher("views/auth/login.jsp").forward(req, resp);
        }
        
    }
    
    
}