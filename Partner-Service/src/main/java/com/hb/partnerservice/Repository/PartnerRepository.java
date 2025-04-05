package com.hb.partnerservice.Repository;

import com.hb.partnerservice.Model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, UUID>
{
    Optional<Partner> findByEmail(String email);
}
