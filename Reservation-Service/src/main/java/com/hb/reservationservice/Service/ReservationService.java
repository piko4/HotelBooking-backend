package com.hb.reservationservice.Service;

import com.hb.reservationservice.Controller.PropertyController;
import com.hb.reservationservice.Model.Property;
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

    @Autowired
    private PropertyService propertyService;

    public Reservation createReservation(Reservation reservation)
    {
        // Set booking status
        reservation.setStatus("BOOKED");

        // Fetch the property by ID
        Property property = propertyService.getPropertyById(reservation.getPropertyId());

        if (property == null) {
            throw new RuntimeException("Property not found with ID: " + reservation.getPropertyId());
        }

        // Calculate number of rooms booked
        int roomsBooked = reservation.getNumberOfRooms(); // Assuming this field exists

        // Check for room availability
        if (property.getAvailableRooms() < roomsBooked) {
            throw new RuntimeException("Not enough rooms available");
        }

        // Update reserved and available room counts
        property.setReservedRooms(property.getReservedRooms() + roomsBooked);
        property.setAvailableRooms(property.getAvailableRooms() - roomsBooked);

        // Persist the updated property
        propertyService.updateProperty(property); // Ensure this method exists

        // Save the reservation
        return reservationRepository.save(reservation);
    }

    //----------------get all reservations----------------
    public List<Reservation> getAllReservations()
    {
        return reservationRepository.findAll();
    }

    //----------------get a reservation bu it's id----------------
    public Optional<Reservation> getReservationById(UUID id)
    {
        return reservationRepository.findById(id);
    }

    //----------------get all reservations by user id----------------
    public List<Reservation> getReservationsByUserId(UUID userId)
    {
        return reservationRepository.findByUserId(userId);
    }

    //----------------get all reservations by property id----------------
    public List<Reservation> getReservationsByPropertyId(UUID propertyId)
    {
        return reservationRepository.findByPropertyId(propertyId);
    }

    //----------------cancel and delete a reservation by passing it's id----------------
    public void cancelReservation(UUID id)
    {
        reservationRepository.findById(id).ifPresent(reservation -> {
            // Only cancel if it's not already cancelled
            if (!"CANCELLED".equalsIgnoreCase(reservation.getStatus())) {
                // Update reservation status
                reservation.setStatus("CANCELLED");
                reservationRepository.save(reservation); // Save updated status

                // Fetch and update property
                Property property = propertyService.getPropertyById(reservation.getPropertyId());
                int roomsToFree = reservation.getNumberOfRooms();

                // Adjust counts safely
                property.setReservedRooms(Math.max(0, property.getReservedRooms() - roomsToFree));
                property.setAvailableRooms(property.getAvailableRooms() + roomsToFree);

                propertyService.updateProperty(property);
            }
        });
    }

}
