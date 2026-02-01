package com.ongconnect.controller;

import com.ongconnect.dao.DonationDAO;
import com.ongconnect.model.Donation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/donate/confirm")
public class DonateConfirmServlet extends HttpServlet {

    private DonationDAO donationDAO = new DonationDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Long caseId = Long.parseLong(req.getParameter("caseId"));
            double montant = Double.parseDouble(req.getParameter("montant"));

            Donation d = new Donation();
            d.setCaseId(caseId);
            d.setMontant(montant);

            // 🔹 Donateur ANONYME
            d.setDonorId(null);

            donationDAO.save(d);

            resp.sendRedirect(req.getContextPath() + "/views/public/donation-success.jsp");

        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
