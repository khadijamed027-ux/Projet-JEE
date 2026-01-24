package com.ongconnect.controller;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.ONG;
import com.ongconnect.model.StatutValidation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.ongconnect.model.Role;
import com.ongconnect.model.User;
import com.ongconnect.model.CaseReport;
import com.ongconnect.dao.CaseReportDAO;

import java.io.IOException;
@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private final ONGDAO ongDAO = new ONGDAO();
    private final CaseReportDAO caseDAO = new CaseReportDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User admin = (User) req.getSession().getAttribute("user");
        if(admin == null || admin.getRole() != Role.ADMIN) {
            resp.sendRedirect(req.getContextPath() + "/jsp/auth/login.jsp");
            return;
        }

        // 🔹 ONG validées (statut non null et user_id != null)
        req.setAttribute("ongs", ongDAO.findAll()); // Assurez-vous que findAll() retourne toutes les ONG

        // 🔹 Cas signalés (ong_id NULL et statut SIGNALE)
        req.setAttribute("cases", caseDAO.findCasesToAssign());

        req.getRequestDispatcher("/jsp/admin/admin-dashboard.jsp")
           .forward(req, resp);
    }
}