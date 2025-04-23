package com.hb.reservationservice.Repository;

import com.hb.reservationservice.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID>
{
    List<Reservation> findByUserId(UUID userId);

    List<Reservation> findByPropertyId(UUID propertyId);
}
