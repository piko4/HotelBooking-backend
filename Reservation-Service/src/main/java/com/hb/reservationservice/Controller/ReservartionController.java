package com.hb.reservationservice.Controller;

import com.hb.reservationservice.Model.Reservation;
import com.hb.reservationservice.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservartionController
{


    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable UUID id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Reservation> getReservationsByUserId(@PathVariable UUID userId) {
        return reservationService.getReservationsByUserId(userId);
    }

    @GetMapping("/property/{propertyId}")
    public List<Reservation> getReservationsByPropertyId(@PathVariable UUID propertyId) {
        return reservationService.getReservationsByPropertyId(propertyId);
    }

    @PutMapping("/cancel/{id}")
    public void cancelReservation(@PathVariable UUID id) {
        reservationService.cancelReservation(id);
    }
}
