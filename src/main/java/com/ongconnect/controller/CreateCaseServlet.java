package com.ongconnect.controller;

import java.io.IOException;

import com.ongconnect.dao.NotificationDAO;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.CaseReport;
import com.ongconnect.model.CaseStatus;
import com.ongconnect.model.NiveauUrgence;
import com.ongconnect.model.Notification;
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
    private NotificationDAO notifDAO = new NotificationDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null || user.getRole() != Role.ONG) {
            resp.sendRedirect(req.getContextPath()+"/views/auth/login.jsp");
            return;
        }

        ONG ong = ongDAO.findByUserId(user.getId());

        CaseReport c = new CaseReport();

        c.setTitre(req.getParameter("titre"));
        c.setDescription(req.getParameter("description"));
        c.setLocalisation(req.getParameter("localisation"));

        c.setTypeCase(
            TypeCase.valueOf(req.getParameter("typeCase"))
        );

        c.setNiveauUrgence(
            NiveauUrgence.valueOf(req.getParameter("niveauUrgence"))
        );

        c.setStatut(CaseStatus.EN_COURS);
        c.setOngId(ong.getId());

        // SAUVEGARDE DU CAS
        caseService.createCase(c);

        // 🔔 NOTIFICATION SI ÉLEVÉE OU CRITIQUE
        if (c.getNiveauUrgence() == NiveauUrgence.ELEVEE ||
            c.getNiveauUrgence() == NiveauUrgence.CRITIQUE) {

            Notification n = new Notification();
            n.setMessage("🚨 Nouveau cas URGENT : " + c.getTitre());
            n.setUserId(1L); // ADMIN

            notifDAO.save(n);
        }

        resp.sendRedirect(req.getContextPath()+"/ong/dashboard");
    }
}
