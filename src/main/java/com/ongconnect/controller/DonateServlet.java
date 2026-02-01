package com.ongconnect.controller;

import java.io.IOException;

import com.ongconnect.model.CaseReport;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.impl.CaseServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/donate")
public class DonateServlet extends HttpServlet {

    private CaseService caseService = new CaseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long caseId;

        try {
            caseId = Long.parseLong(req.getParameter("caseId"));
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        CaseReport c = caseService.getCaseById(caseId);

        if (c == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        req.setAttribute("cas", c);
        req.getRequestDispatcher("/views/public/donate.jsp")
           .forward(req, resp);
    }
}
