package com.ongconnect.controller;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.*;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.impl.CaseServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/ong/dashboard")
public class OngDashboardServlet extends HttpServlet {

    private final ONGDAO ongDAO = new ONGDAO();
    private CaseService caseService = new CaseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        // 🔐 Sécurité
        if (user == null || user.getRole() != Role.ONG) {
            resp.sendRedirect(req.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        // 🔍 ONG liée à l’utilisateur
        

        ONG ong = ongDAO.findByUserId(user.getId());

        if (ong == null) {
            resp.sendRedirect(req.getContextPath() + "/ong/create-ong.jsp");
            return;
        }


        // 📦 Tous les cas de l’ONG
        List<CaseReport> cases = caseService.getCasesForOng(ong.getId());

        req.setAttribute("ong", ong);
        req.setAttribute("cases", cases);

        req.getRequestDispatcher("/views/ong/ong-dashboard.jsp")
           .forward(req, resp);
    }
}
