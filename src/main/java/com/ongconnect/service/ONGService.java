package com.ongconnect.service;

import com.ongconnect.model.ONG;
import java.util.List;

public interface ONGService {

    List<ONG> getValidatedONGs();

    void validateONG(Long ongId);
}
