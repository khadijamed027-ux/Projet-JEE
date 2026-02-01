package com.ongconnect.controller;

import java.io.IOException;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.CaseReport;
import com.ongconnect.model.CaseStatus;
import com.ongconnect.model.ONG;
import com.ongconnect.model.Role;
import com.ongconnect.model.StatutValidation;
import com.ongconnect.model.TypeCase;
import com.ongconnect.model.User;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.impl.CaseServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ong/case/create")
public class CreateCaseServlet extends HttpServlet {

	private CaseService caseService = new CaseServiceImpl();    
	private ONGDAO ongDAO = new ONGDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        // Sécurité
        if (user == null || user.getRole() != Role.ONG) {
            resp.sendRedirect(req.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        ONG ong = ongDAO.findByUserId(user.getId());

        CaseReport c = new CaseReport();
        c.setTitre(req.getParameter("titre"));
        String titre = req.getParameter("titre");
        String description = req.getParameter("description");

        if (titre == null || titre.trim().isEmpty()
                || description == null || description.trim().isEmpty()) {

            req.setAttribute("error", "Tous les champs sont obligatoires");
            req.getRequestDispatcher("/views/ong/ong-dashboard.jsp")
               .forward(req, resp);
            return;
        }

        c.setDescription(req.getParameter("description"));
        c.setLocalisation(req.getParameter("localisation"));
        c.setTypeCase(TypeCase.valueOf(req.getParameter("typeCase")));
        
        if (ong.getStatutValidation() != StatutValidation.VALIDEE) {
            req.setAttribute("error", "Votre ONG n'est pas encore validée par l'administrateur.");
            req.getRequestDispatcher("/views/ong/ong-dashboard.jsp")
               .forward(req, resp);
            return;
        }


        // ✅ LOGIQUE MÉTIER
        c.setStatut(CaseStatus.EN_COURS); // ou EN_COURS
        c.setOngId(ong.getId());

        caseService.createCase(c);

        resp.sendRedirect(req.getContextPath() + "/ong/dashboard");
    }
}
