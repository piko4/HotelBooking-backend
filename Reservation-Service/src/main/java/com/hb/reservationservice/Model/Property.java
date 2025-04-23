package com.hb.reservationservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Property
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String title; // Name of the property

    private String description;

    private String type; // Hotel, Villa, Apartment, etc.

    private String address;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    private int totalRooms;
    private int reservedRooms;
    private int availableRooms;

    private double pricePerNight;

    private boolean available = true;

    private String imageUrl;

    // Relationship with Partner
    private UUID partnerId; // You can use this to identify which partner owns this property

    // Timestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate()
    {
        this.updatedAt = LocalDateTime.now();
    }

    //------------------------------reviews-------------
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

}
