package com.hb.reservationservice.Controller;

import com.hb.reservationservice.Model.Review;
import com.hb.reservationservice.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController
{
    @Autowired
    private ReviewService reviewService;

    // Create a new review
    @PostMapping("/create")
    public Review createReview(@RequestBody Review reviewRequest) {
        return reviewService.createReview(reviewRequest.getPropertyId(), reviewRequest);
    }


    // Get reviews for a property
    @GetMapping("/property/{propertyId}")
    public List<Review> getReviewsByPropertyId(@PathVariable UUID propertyId) {
        return reviewService.getReviewsByPropertyId(propertyId);
    }

    // New method: Get average rating for a property
    @GetMapping("/property/{propertyId}/average-rating")
    public Double getAverageRatingByPropertyId(@PathVariable UUID propertyId) {
        return reviewService.getAverageRatingByPropertyId(propertyId);
    }

}
