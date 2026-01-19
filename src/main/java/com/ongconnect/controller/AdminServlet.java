package com.ongconnect.controller;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.ONG;
import com.ongconnect.model.StatutValidation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/validateONG")
public class AdminServlet extends HttpServlet {

    private final ONGDAO ongDAO = new ONGDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ONG ong = new ONG();
        ong.setNom(request.getParameter("nom"));
        ong.setDescription(request.getParameter("description"));
        ong.setStatutValidation(StatutValidation.VALIDEE);

        ongDAO.save(ong);
        response.sendRedirect("../admin.jsp");
    }
}