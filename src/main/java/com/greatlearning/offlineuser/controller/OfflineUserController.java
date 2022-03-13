package com.greatlearning.offlineuser.controller;

import com.greatlearning.offlineuser.entity.Booking;
import com.greatlearning.offlineuser.entity.PaymentMode;
import com.greatlearning.offlineuser.foodItem.FoodItem;
import com.greatlearning.offlineuser.service.OfflineUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class OfflineUserController {

    final OfflineUserService offlineUserService;

    public OfflineUserController(OfflineUserService offlineUserService) {
        this.offlineUserService = offlineUserService;
    }

    @GetMapping("/menu")
    public ResponseEntity<List<FoodItem>> getMenu() {
        return ResponseEntity.ok(offlineUserService.getMenu());
    }

    @PostMapping(value = "/bookings")
    public ResponseEntity<Booking> createBooking(@RequestParam LocalDate bookingDate) {
        return ResponseEntity.ok(offlineUserService.createBooking(bookingDate));
    }

    @PostMapping("bookings/order/{bookingId}")
    public ResponseEntity<Booking> createOrder(@PathVariable UUID bookingId, @RequestBody Integer[] items) {
        return ResponseEntity.ok(offlineUserService.createOrder(bookingId, items));
    }

    @GetMapping("bookings/bill/{bookingId}")
    public ResponseEntity<Float> getBill(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(offlineUserService.getBill(bookingId));
    }

    @PutMapping("bookings/payment/{bookingId}")
    public ResponseEntity<Booking> updatePayment(@PathVariable UUID bookingId, @RequestBody PaymentMode paymentMode) {
        return ResponseEntity.ok(offlineUserService.setPayment(bookingId, paymentMode));
    }

    @PutMapping("bookings/feedback/{bookingId}")
    public ResponseEntity<Booking> updateFeedback(@PathVariable UUID bookingId, @RequestBody String feedback) {
        return ResponseEntity.ok(offlineUserService.setFeedback(bookingId, feedback));
    }
}
