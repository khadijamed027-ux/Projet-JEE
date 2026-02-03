package com.ongconnect.controller;

import java.io.IOException;

import com.ongconnect.dao.CaseReportDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/case/delete")
public class DeleteCaseServlet extends HttpServlet {

    private CaseReportDAO dao = new CaseReportDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Long id = Long.parseLong(req.getParameter("id"));

        dao.delete(id);

        resp.sendRedirect(req.getContextPath() + "/ong/dashboard");
    }
}
