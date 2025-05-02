package com.hb.reservationservice.Service;

import com.hb.reservationservice.Model.Wishlist;
import com.hb.reservationservice.Repository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public Wishlist addToWishlist(UUID userId, UUID propertyId) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setPropertyId(propertyId);
        wishlist.setCreatedAt(LocalDateTime.now());
        return wishlistRepository.save(wishlist);
    }

    @Transactional
    public void removeFromWishlist(UUID userId, UUID propertyId) {
        wishlistRepository.deleteByUserIdAndPropertyId(userId, propertyId);
    }

    public List<Wishlist> getUserWishlist(UUID userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public boolean isInWishlist(UUID userId, UUID propertyId) {
        return wishlistRepository.findByUserIdAndPropertyId(userId, propertyId).isPresent();
    }
}

