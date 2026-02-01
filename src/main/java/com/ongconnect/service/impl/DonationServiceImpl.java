package com.ongconnect.service.impl;

import com.ongconnect.dao.DonationDAO;
import com.ongconnect.model.Donation;
import com.ongconnect.service.DonationService;

public class DonationServiceImpl implements DonationService {

    private DonationDAO donationDAO = new DonationDAO();

    @Override
    public void saveDonation(Donation donation) {
        donationDAO.save(donation);
    }

    @Override
    public int countDonations() {
        return donationDAO.countDonations();
    }
}
