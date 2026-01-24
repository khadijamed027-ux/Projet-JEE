package com.ongconnect.controller;

import com.ongconnect.dao.CaseReportDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/assign")
public class AssignCaseServlet extends HttpServlet {

    private final CaseReportDAO dao = new CaseReportDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        long caseId = Long.parseLong(req.getParameter("caseId"));
        long ongId = Long.parseLong(req.getParameter("ongId"));

        dao.assignCaseToOng(caseId, ongId);

        resp.sendRedirect(req.getContextPath() + "/admin/cases");
    }
}