package com.ongconnect.controller;

import java.io.IOException;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.StatutValidation;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/validateONG")
public class ValidateONGServlet extends HttpServlet {

    private ONGDAO ongDAO = new ONGDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Long ongId;
        try {
            ongId = Long.parseLong(req.getParameter("ongId"));
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            return;
        }

        ongDAO.updateStatut(ongId, StatutValidation.VALIDEE);

        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }

}

