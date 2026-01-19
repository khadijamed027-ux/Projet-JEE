package com.ongconnect.service;

import com.ongconnect.dao.ONGDAO;
import com.ongconnect.model.ONG;

import java.util.List;

public class ONGService {

    private ONGDAO ongDAO = new ONGDAO();

    public List<ONG> getPendingONGs() {
        return ongDAO.findPending();
    }

    public void validateONG(long id, String status) {
        ongDAO.updateStatus(id, status);
    }
}