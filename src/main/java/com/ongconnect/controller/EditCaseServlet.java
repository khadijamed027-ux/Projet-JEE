package com.ongconnect.controller;

import com.ongconnect.dao.CaseReportDAO;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.CaseReport;
import com.ongconnect.model.ONG;
import com.ongconnect.model.Role;
import com.ongconnect.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/editCase")
public class EditCaseServlet extends HttpServlet {

    private CaseReportDAO dao = new CaseReportDAO();
    private ONGDAO ongDAO = new ONGDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null || user.getRole() != Role.ONG) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        ONG ong = ongDAO.findByUserId(user.getId());

        Long id = Long.parseLong(req.getParameter("id"));
        CaseReport c = dao.findById(id);

        // ➤ SÉCURITÉ : vérifier que ce cas appartient à l’ONG connectée
        if (!c.getOngId().equals(ong.getId())) {
            resp.sendRedirect(req.getContextPath() + "/ong/dashboard");
            return;
        }

        req.setAttribute("cas", c);

        req.getRequestDispatcher("/views/ong/edit-case.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        CaseReport c = new CaseReport();

        c.setId(Long.parseLong(req.getParameter("id")));
        c.setTitre(req.getParameter("titre"));
        c.setDescription(req.getParameter("description"));
        c.setLocalisation(req.getParameter("localisation"));
        c.setObjectif(Double.parseDouble(req.getParameter("objectif")));

        dao.update(c);

        resp.sendRedirect(req.getContextPath() + "/ong/dashboard");
    }
}
