package com.ongconnect.controller;

import java.io.IOException;

import com.ongconnect.dao.ONGDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private ONGDAO ongDAO = new ONGDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("ongs", ongDAO.findValidated());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
