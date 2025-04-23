package com.hb.reservationservice.Repository;

import com.hb.reservationservice.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID>
{
    List<Property> findByTypeIgnoreCase(String type);

    List<Property> findAllByPartnerId(UUID partnerId);

    List<Property> findAllByAddressContainsIgnoreCase(String location);
}
