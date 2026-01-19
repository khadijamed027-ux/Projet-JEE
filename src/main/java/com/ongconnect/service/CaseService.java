package com.ongconnect.service;

import com.ongconnect.dao.CaseReportDAO;
import com.ongconnect.model.CaseReport;

import java.util.List;

public class CaseService {

    private CaseReportDAO caseDAO = new CaseReportDAO();

    public void createCase(CaseReport c) {
        caseDAO.save(c);
    }

    public List<CaseReport> getCasesByONG(long ongId) {
        return caseDAO.findByONG(ongId);
    }
}