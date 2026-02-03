package com.ongconnect.controller;

import com.ongconnect.dao.DonationDAO;
import com.ongconnect.dao.NotificationDAO;
import com.ongconnect.model.CaseReport;
import com.ongconnect.model.Donation;
import com.ongconnect.model.Notification;
import com.ongconnect.model.User;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.impl.CaseServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/donate/confirm")
public class DonateConfirmServlet extends HttpServlet {

    private DonationDAO dao = new DonationDAO();
    private CaseService caseService = new CaseServiceImpl();

    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException, ServletException {

        Long caseId = Long.parseLong(req.getParameter("caseId"));
        double montant = Double.parseDouble(req.getParameter("montant"));

        CaseReport c = caseService.getCaseById(caseId);

        if (c.getTotalDons() >= c.getObjectif()) {

            req.setAttribute("erreur",
              "❌ Objectif atteint, impossible de faire un don.");

            req.setAttribute("cas", c);

            req.getRequestDispatcher("/views/public/donate.jsp")
               .forward(req, resp);
            return;
        }

        Donation d = new Donation();
        d.setCaseId(caseId);
        d.setMontant(montant);

        dao.save(d);

        resp.sendRedirect(req.getContextPath() + "/views/public/merci.jsp");

    }
}
