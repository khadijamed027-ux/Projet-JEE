package com.ongconnect.controller;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.*;
import com.ongconnect.service.CaseService;
import com.ongconnect.service.impl.CaseServiceImpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/ong/export/pdf")
public class ExportOngCasesPdfServlet extends HttpServlet {

	private CaseService caseService = new CaseServiceImpl();
    private ONGDAO ongDAO = new ONGDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.getRole() != Role.ONG) {
            resp.sendRedirect(req.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        ONG ong = ongDAO.findByUserId(user.getId());
        List<CaseReport> cases =
                caseService.getCasesForOng(ong.getId());


        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition",
                "attachment; filename=cas_ong.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();

            document.add(new Paragraph("ONG : " + ong.getNom()));
            document.add(new Paragraph("Liste des cas reçus"));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.addCell("Titre");
            table.addCell("Localisation");
            table.addCell("Statut");
            table.addCell("Type");

            for (CaseReport c : cases) {
                table.addCell(c.getTitre());
                table.addCell(c.getLocalisation());
                table.addCell(c.getStatut().name());
                table.addCell(
                    c.getTypeCase() != null ? c.getTypeCase().name() : "-"
                );
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
