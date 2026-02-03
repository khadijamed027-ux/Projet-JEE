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

@WebServlet("/admin/rejectONG")
public class RejectONGServlet extends HttpServlet {

    private ONGDAO ongDAO = new ONGDAO();
    private NotificationDAO notifDAO = new NotificationDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Long ongId = Long.parseLong(req.getParameter("ongId"));

        ongDAO.updateStatut(ongId, StatutValidation.REFUSEE);

        Notification n = new Notification();
        n.setMessage("Votre ONG a été REFUSÉE");
        n.setOngId(ongId);
        n.setLu(false);

        notifDAO.save(n);

        resp.sendRedirect("dashboard");
    }
}
