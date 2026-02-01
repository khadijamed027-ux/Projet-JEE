package com.ongconnect.controller;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.ONG;
import com.ongconnect.model.Role;
import com.ongconnect.model.StatutValidation;
import com.ongconnect.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/ong/create")
public class CreateONGServlet extends HttpServlet {

    private final ONGDAO ongDAO = new ONGDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        String domaine = req.getParameter("domaine");

        if (nom == null || domaine == null) {
            req.setAttribute("error", "Données manquantes");
            req.getRequestDispatcher("/views/ong/create-ong.jsp").forward(req, resp);
            return;
        }

        ONG ong = new ONG();
        ong.setNom(nom);
        ong.setDescription(description);
        ong.setDomaine(domaine);
        ong.setUserId(user.getId());
        ong.setStatutValidation(StatutValidation.EN_ATTENTE);

        System.out.println("INSERT ONG POUR USER ID = " + user.getId());
        System.out.println("==== CREATE ONG ====");
        System.out.println("User id: " + user.getId());
        System.out.println("Nom ONG: " + nom);
        System.out.println("Domaine: " + domaine);


        ongDAO.create(ong);

        resp.sendRedirect(req.getContextPath() + "/ong/dashboard");
    }

}