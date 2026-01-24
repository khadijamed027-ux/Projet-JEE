package com.ongconnect.controller;

import com.ongconnect.model.CaseReport;
import com.ongconnect.model.CaseStatus;
import com.ongconnect.model.User;
import com.ongconnect.service.CaseService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/case/create")
public class CaseReportServlet extends HttpServlet {
	

    private CaseService caseService = new CaseService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Vérifier si l'utilisateur est connecté
        HttpSession session = req.getSession(false); // ne crée pas de session si elle n'existe pas
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/auth/login.jsp");
            return; // arrêter l'exécution si pas connecté
        }

        User user = (User) session.getAttribute("user");

        // Création du cas
        CaseReport c = new CaseReport();
        c.setTitre(req.getParameter("titre"));
        c.setDescription(req.getParameter("description"));
        c.setLocalisation(req.getParameter("localisation"));
        c.setStatut(CaseStatus.SIGNALE);
        c.setUserId(user.getId());

        caseService.createCase(c);

        // Redirection vers le dashboard utilisateur
        resp.sendRedirect(req.getContextPath() + "/jsp/user/dashboard.jsp");
    }
}