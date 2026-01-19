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

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        CaseReport c = new CaseReport();
        c.setTitre(req.getParameter("titre"));
        c.setDescription(req.getParameter("description"));
        c.setLocalisation(req.getParameter("localisation"));
        c.setStatut(CaseStatus.SIGNALE);
        c.setUserId(user.getId());

        caseService.createCase(c);
        resp.sendRedirect("../jsp/user/dashboard.jsp");
    }
}