package com.ongconnect.controller;

import com.ongconnect.dao.CaseReportDAO;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.*;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.impl.CaseServiceImpl;
import com.ongconnect.dao.DonationDAO;
import com.ongconnect.dao.NotificationDAO;

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
    private NotificationDAO notifDAO = new NotificationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User admin = (User) req.getSession().getAttribute("user");

        if (admin == null || admin.getRole() != Role.ADMIN) {
            resp.sendRedirect(req.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        // ======= STATISTIQUES SIMPLES =======
        int totalOngs = ongDAO.countONGs();
        int totalCases = caseService.countCases();
        int totalDonations = donationDAO.countDonations();

        req.setAttribute("totalOngs", totalOngs);
        req.setAttribute("totalCases", totalCases);
        req.setAttribute("totalDonations", totalDonations);

        req.setAttribute("badgeAdmin",
                notifDAO.countUnreadByUser(admin.getId()));

        req.setAttribute("pendingOngs", ongDAO.findPending());

        // ======= DONNÉES POUR GRAPHIQUES =======

        // 1. Répartition des cas par urgence
        req.setAttribute("critique",
                new CaseReportDAO().countByUrgence("CRITIQUE"));

        req.setAttribute("elevee",
                new CaseReportDAO().countByUrgence("ELEVEE"));

        req.setAttribute("normale",
                new CaseReportDAO().countByUrgence("NORMALE"));

        // 2. Dons par mois
        req.setAttribute("donsJanvier",
                donationDAO.sumByMonth(1));

        req.setAttribute("donsFevrier",
                donationDAO.sumByMonth(2));

        req.setAttribute("donsMars",
                donationDAO.sumByMonth(3));

        req.getRequestDispatcher("/views/admin/admin-dashboard.jsp")
           .forward(req, resp);
    }
}
