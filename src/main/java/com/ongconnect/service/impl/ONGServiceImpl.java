package com.ongconnect.service.impl;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.ONG;
import com.ongconnect.model.StatutValidation;
import com.ongconnect.service.ONGService;

import java.util.List;

public class ONGServiceImpl implements ONGService {

    private final ONGDAO ongDAO = new ONGDAO();

    @Override
    public List<ONG> getValidatedONGs() {
        return ongDAO.findValidated();
    }

    @Override
    public void validateONG(Long ongId) {
        ongDAO.updateStatut(ongId, StatutValidation.VALIDEE);
    }
}
