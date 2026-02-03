package com.ongconnect.controller;

import com.ongconnect.dao.NotificationDAO;
import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.Notification;
import com.ongconnect.model.Role;
import com.ongconnect.model.User;
import com.ongconnect.model.ONG;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/notifications")
public class NotificationServlet extends HttpServlet {

    private NotificationDAO notificationDAO = new NotificationDAO();
    private ONGDAO ongDAO = new ONGDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect("views/auth/login.jsp");
            return;
        }

        List<Notification> notifs;

        // 🔐 SI ADMIN
        if (user.getRole() == Role.ADMIN) {

            notifs = notificationDAO.findByUser(user.getId());

            // 👉 ICI ON MARQUE COMME LUES
            notificationDAO.markReadByUser(user.getId());
        }

        // 🟢 SI ONG
        else if (user.getRole() == Role.ONG) {

            ONG ong = ongDAO.findByUserId(user.getId());

            notifs = notificationDAO.findByOng(ong.getId());

            // 👉 ICI AUSSI
            notificationDAO.markReadByOng(ong.getId());
        }

        // 👤 DONATEUR (si tu utilises)
        else {
            notifs = notificationDAO.findByUser(user.getId());

            notificationDAO.markReadByUser(user.getId());
        }

        req.setAttribute("notifications", notifs);

        req.getRequestDispatcher("/views/notifications.jsp")
           .forward(req, resp);
    }
}
