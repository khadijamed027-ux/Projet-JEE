package com.ongconnect.service;

import com.ongconnect.model.Donation;

public interface DonationService {

    void saveDonation(Donation donation);

    // SI tu veux une statistique des dons :
    int countDonations();
}
