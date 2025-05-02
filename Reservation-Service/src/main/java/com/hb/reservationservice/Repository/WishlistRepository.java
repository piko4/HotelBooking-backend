package com.hb.reservationservice.Repository;

import com.hb.reservationservice.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, UUID>
{
    List<Wishlist> findByUserId(UUID userId);
    Optional<Wishlist> findByUserIdAndPropertyId(UUID userId, UUID propertyId);
    void deleteByUserIdAndPropertyId(UUID userId, UUID propertyId);
}
