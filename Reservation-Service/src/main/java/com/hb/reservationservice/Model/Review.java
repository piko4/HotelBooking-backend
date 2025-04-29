package com.hb.reservationservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String userName;
    private UUID userId;
    private int rating; // from 1 to 5
    private String comment;

    private UUID propertyId;

    private LocalDateTime createdAt = LocalDateTime.now();
}

