package com.hb.partnerservice.Service;

import com.hb.partnerservice.Model.Partner;
import com.hb.partnerservice.Repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PartnerService
{
    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Partner registerPartner(Partner partner)
    {
        partner.setPassword(passwordEncoder.encode(partner.getPassword())); // Encrypt password
        return partnerRepository.save(partner);
    }

    public Optional<Partner> findByEmail(String email)
    {
        return partnerRepository.findByEmail(email);
    }

    public Partner findByID(UUID id)
    {
        return partnerRepository.findById(id).orElse(null);
    }
}
