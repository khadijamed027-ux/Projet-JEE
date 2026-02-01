package com.ongconnect.service.impl;

import com.ongconnect.dao.CaseReportDAO;
import com.ongconnect.model.CaseReport;
import com.ongconnect.service.CaseService;

import java.util.List;

public class CaseServiceImpl implements CaseService {

    private final CaseReportDAO caseDAO = new CaseReportDAO();

    @Override
    public void createCase(CaseReport c) {
        caseDAO.create(c);
    }

    @Override
    public List<CaseReport> getCasesForOng(Long ongId) {
        return caseDAO.findCasesForOng(ongId);
    }

    @Override
    public List<CaseReport> getPublicCasesByOng(Long ongId) {
        return caseDAO.findPublicCasesByOng(ongId);
    }

    @Override
    public List<CaseReport> getAllPublicCases() {
        return caseDAO.findPublicCases();
    }
    @Override
    public CaseReport getCaseById(Long id) {
        return caseDAO.findById(id);
    }
    @Override
    public int countCases() {
        return caseDAO.countCases();
    }

}
