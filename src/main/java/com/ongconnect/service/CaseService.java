package com.ongconnect.service;

import com.ongconnect.model.CaseReport;
import java.util.List;

public interface CaseService {

    void createCase(CaseReport c);

    List<CaseReport> getCasesForOng(Long ongId);

    List<CaseReport> getPublicCasesByOng(Long ongId);

    List<CaseReport> getAllPublicCases();
    
    CaseReport getCaseById(Long id);
    int countCases();

}
