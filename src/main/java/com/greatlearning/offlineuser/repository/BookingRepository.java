package com.greatlearning.offlineuser.repository;

import com.greatlearning.offlineuser.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
