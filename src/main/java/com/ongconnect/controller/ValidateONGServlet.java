package com.ongconnect.controller;

import java.io.IOException;

import com.ongconnect.dao.NotificationDAO;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.Notification;
import com.ongconnect.model.StatutValidation;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/validateONG")
public class ValidateONGServlet extends HttpServlet {

    private ONGDAO ongDAO = new ONGDAO();
    private NotificationDAO notifDAO = new NotificationDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Long ongId = Long.parseLong(req.getParameter("ongId"));

        ongDAO.updateStatut(ongId, StatutValidation.VALIDEE);

        // 🔔 notif ONG
        Notification n = new Notification();
        n.setMessage("Votre ONG a été VALIDÉE par l'administrateur");
        n.setOngId(ongId);
        n.setLu(false);

        notifDAO.save(n);

        resp.sendRedirect("dashboard");
    }
}

