package com.ongconnect.controller;

import java.io.IOException;

import com.ongconnect.dao.CaseReportDAO;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.impl.CaseServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ong/public")
public class PublicOngServlet extends HttpServlet {

	private CaseService caseService = new CaseServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    	Long ongId = null;

    	try {
    	    ongId = Long.parseLong(req.getParameter("id"));
    	} catch (Exception e) {
    	    resp.sendRedirect(req.getContextPath() + "/index.jsp");
    	    return;
    	}


        req.setAttribute("cases",
                caseService.getPublicCasesByOng(ongId));

        req.getRequestDispatcher("/views/public/ong-cases.jsp")
           .forward(req, resp);
    }
}
