package com.ongconnect.controller;

import com.ongconnect.dao.DonationDAO;
import com.ongconnect.dao.NotificationDAO;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.*;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.impl.CaseServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ong/dashboard")
public class OngDashboardServlet extends HttpServlet {

    private final ONGDAO ongDAO = new ONGDAO();
    private CaseService caseService = new CaseServiceImpl();
    private DonationDAO donationDAO = new DonationDAO();
    private NotificationDAO notifDAO = new NotificationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null || user.getRole() != Role.ONG) {
            resp.sendRedirect(req.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        ONG ong = ongDAO.findByUserId(user.getId());

        if (ong == null) {
            resp.sendRedirect(req.getContextPath() + "/views/ong/create-ong.jsp");
            return;
        }

        if (ong.getStatutValidation() == StatutValidation.EN_ATTENTE) {
            req.getRequestDispatcher("/views/ong/attente.jsp").forward(req, resp);
            return;
        }

        if (ong.getStatutValidation() == StatutValidation.REFUSEE) {
            req.getRequestDispatcher("/views/ong/refuse.jsp").forward(req, resp);
            return;
        }
        

        // 🔹 Récupérer les cas de l’ONG
     // 🔹 PAGINATION DES CAS DE L’ONG
        int page = 1;
        int size = 5;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        List<CaseReport> all = caseService.getCasesForOng(ong.getId());
        

        System.out.println("==== CAS TROUVÉS POUR ONG " + ong.getId());
        System.out.println(all.size());


        int from = (page - 1) * size;
        int to = Math.min(from + size, all.size());

        List<CaseReport> cases = all.subList(from, to);

        int totalPages = (int) Math.ceil((double) all.size() / size);

        req.setAttribute("cases", cases);
        req.setAttribute("page", page);
        req.setAttribute("totalPages", totalPages);

        // 🔹 Récupérer les dons pour chaque cas
        Map<Long, List<Donation>> donsParCas = new HashMap<>();

        for (CaseReport c : cases) {
            donsParCas.put(c.getId(),
                donationDAO.findByCase(c.getId()));
        }

        req.setAttribute("ong", ong);
        
        req.setAttribute("donsParCas", donsParCas);
        req.setAttribute("badgeOng",
                notifDAO.countUnreadByOng(ong.getId()));



        req.getRequestDispatcher("/views/ong/ong-dashboard.jsp")
           .forward(req, resp);
    }
}
