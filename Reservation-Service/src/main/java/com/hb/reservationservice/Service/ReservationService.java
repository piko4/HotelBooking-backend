package com.hb.reservationservice.Service;

import com.hb.reservationservice.Model.Reservation;
import com.hb.reservationservice.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService
{


    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation)
    {
        reservation.setStatus("BOOKED");
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations()
    {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(UUID id)
    {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getReservationsByUserId(UUID userId)
    {
        return reservationRepository.findByUserId(userId);
    }

    public List<Reservation> getReservationsByPropertyId(UUID propertyId)
    {
        return reservationRepository.findByPropertyId(propertyId);
    }

    public void cancelReservation(UUID id)
    {
        reservationRepository.findById(id).ifPresent(reservation -> {
            reservation.setStatus("CANCELLED");
            reservationRepository.delete(reservation);
        });
    }
}
