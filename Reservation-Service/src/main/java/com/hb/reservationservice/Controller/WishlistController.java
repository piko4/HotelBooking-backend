package com.hb.reservationservice.Controller;

import com.hb.reservationservice.Model.Wishlist;
import com.hb.reservationservice.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wishlist")
public class WishlistController
{
    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<Wishlist> addToWishlist(@RequestParam UUID userId, @RequestParam UUID propertyId) {
        return ResponseEntity.ok(wishlistService.addToWishlist(userId, propertyId));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromWishlist(@RequestParam UUID userId, @RequestParam UUID propertyId) {
        wishlistService.removeFromWishlist(userId, propertyId);
        return ResponseEntity.ok("Removed from wishlist");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlist(@PathVariable UUID userId) {
        return ResponseEntity.ok(wishlistService.getUserWishlist(userId));
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isInWishlist(@RequestParam UUID userId, @RequestParam UUID propertyId) {
        return ResponseEntity.ok(wishlistService.isInWishlist(userId, propertyId));
    }
}
