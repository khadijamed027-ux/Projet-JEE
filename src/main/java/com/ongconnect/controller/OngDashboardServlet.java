package com.ongconnect.controller;

import com.ongconnect.dao.CaseReportDAO;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.CaseReport;
import com.ongconnect.model.Role;
import com.ongconnect.model.User;
import com.ongconnect.model.ONG;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ong/dashboard")
public class OngDashboardServlet extends HttpServlet {

    private final ONGDAO ongDAO = new ONGDAO();
    private final CaseReportDAO caseDAO = new CaseReportDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null || user.getRole() != Role.ONG) {
            resp.sendRedirect(req.getContextPath() + "/jsp/auth/login.jsp");
            return;
        }

        ONG ong = ongDAO.findByUserId(user.getId());
        if (ong == null) {
            req.setAttribute("error", "Aucune ONG trouvée pour cet utilisateur.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        // Tous les cas assignés à cette ONG
        req.setAttribute("cases", caseDAO.findCasesForOng(ong.getId()));

        req.getRequestDispatcher("/jsp/ong/ong-dashboard.jsp").forward(req, resp);
    }
}