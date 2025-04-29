package com.hb.reservationservice.Service;

import com.hb.reservationservice.Model.Property;
import com.hb.reservationservice.Model.Review;
import com.hb.reservationservice.Repository.PropertyRepository;
import com.hb.reservationservice.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService
{


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    // Create a new review
    public Review createReview(UUID propertyId, Review reviewRequest) {
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);

        if (propertyOptional.isEmpty()) {
            throw new IllegalArgumentException("Property not found with ID: " + propertyId);
        }

        reviewRequest.setPropertyId(propertyId);
        return reviewRepository.save(reviewRequest);
    }

    // Get all reviews for a specific property
    public List<Review> getReviewsByPropertyId(UUID propertyId) {
        return reviewRepository.findByPropertyId(propertyId);
    }

    // In ReviewService.java
    public Double getAverageRatingByPropertyId(UUID propertyId) {
        List<Review> reviews = reviewRepository.findByPropertyId(propertyId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        double sum = reviews.stream()
                .mapToDouble(Review::getRating)
                .sum();
        return sum / reviews.size();
    }

}
