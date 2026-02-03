package com.ongconnect.controller;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.dao.CaseReportDAO;
import com.ongconnect.dao.DonationDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/stats")
public class StatsServlet extends HttpServlet {

    private ONGDAO ongDAO = new ONGDAO();
    private CaseReportDAO caseDAO = new CaseReportDAO();
    private DonationDAO donationDAO = new DonationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int ongs = ongDAO.countONGs();
        int cases = caseDAO.countCases();
        int dons = donationDAO.countDonations();

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        out.print("{");
        out.print("\"ongs\":" + ongs + ",");
        out.print("\"cases\":" + cases + ",");
        out.print("\"dons\":" + dons);
        out.print("}");
    }
}
