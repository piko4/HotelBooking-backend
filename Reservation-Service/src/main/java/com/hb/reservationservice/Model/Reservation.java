package com.hb.reservationservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private UUID propertyId; // The property being booked

    private UUID userId;     // The user who booked it

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private int numberOfGuests;
    private int numberOfRooms;
    private double totalPrice;

    private String status; // BOOKED, CANCELLED, COMPLETED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
