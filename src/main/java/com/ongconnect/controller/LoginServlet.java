package com.ongconnect.controller;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.ONG;
import com.ongconnect.model.StatutValidation;
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

                // Vérifier si l'ONG existe et son statut
                ONGDAO ongDAO = new ONGDAO();
                ONG ong = ongDAO.findByUserId(user.getId());

                if (ong == null) {
                    // Pas encore créé son ONG
                    resp.sendRedirect(req.getContextPath() + "/views/ong/create-ong.jsp");
                    return;
                }

                if (ong.getStatutValidation() != StatutValidation.VALIDEE) {
                    // ⛔ PAS VALIDÉ = page d’attente
                    resp.sendRedirect(req.getContextPath() + "/views/ong/attente.jsp");
                    return;
                }

                // ✅ OK → dashboard
                resp.sendRedirect(req.getContextPath() + "/ong/dashboard");
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