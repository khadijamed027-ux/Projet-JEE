package com.ongconnect.controller;

import com.ongconnect.dao.CaseReportDAO;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.*;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.impl.CaseServiceImpl;
import com.ongconnect.dao.DonationDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private final ONGDAO ongDAO = new ONGDAO();
    private CaseService caseService = new CaseServiceImpl();
    private DonationDAO donationDAO = new DonationDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User admin = (User) req.getSession().getAttribute("user");
        if (admin == null || admin.getRole() != Role.ADMIN) {
            resp.sendRedirect(req.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        req.setAttribute("pendingOngs", ongDAO.findPending());

        req.setAttribute("cases", caseService.getAllPublicCases());
        req.setAttribute("totalOngs", ongDAO.countONGs());
        req.setAttribute("totalCases", caseService.countCases());
        req.setAttribute("totalDonations", donationDAO.countDonations());


        req.getRequestDispatcher("/views/admin/admin-dashboard.jsp")
           .forward(req, resp);
        
        
    }
}
