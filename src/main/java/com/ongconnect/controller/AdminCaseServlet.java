package com.ongconnect.controller;

import com.ongconnect.dao.CaseReportDAO;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.CaseReport;
import com.ongconnect.model.ONG;
import com.ongconnect.model.Role;
import com.ongconnect.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/ong-validation")
public class AdminCaseServlet extends HttpServlet {

    private final ONGDAO ongDAO = new ONGDAO();
    private final CaseReportDAO caseDAO = new CaseReportDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User admin = (User) req.getSession().getAttribute("user");

        if (admin == null || admin.getRole() != Role.ADMIN) {
            resp.sendRedirect(req.getContextPath() + "/jsp/auth/login.jsp");
            return;
        }

        // 🔹 ONG validées
        req.setAttribute("ongs", ongDAO.findAll());

        // 🔹 Cas signalés (ong_id NULL)
        req.setAttribute("cases", caseDAO.findCasesToAssign());

        req.getRequestDispatcher("/jsp/admin/ong-validation.jsp")
           .forward(req, resp);
    }
}