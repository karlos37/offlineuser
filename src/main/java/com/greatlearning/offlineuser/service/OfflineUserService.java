package com.greatlearning.offlineuser.service;

import com.greatlearning.offlineuser.entity.Booking;
import com.greatlearning.offlineuser.entity.PaymentMode;
import com.greatlearning.offlineuser.foodItem.FoodItem;
import com.greatlearning.offlineuser.repository.BookingRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.*;

@Service
public class OfflineUserService {

    final RestTemplate restTemplate;

    final BookingRepository bookingRepository;

    @Value("${client.foodItem.url}")
    String foodItemsUrl;

    @Value("${client.foodOrder.url}")
    String foodOrdersUrl;

    public OfflineUserService(RestTemplate restTemplate, BookingRepository bookingRepository) {
        this.restTemplate = restTemplate;
        this.bookingRepository = bookingRepository;
    }

    public List<FoodItem> getMenu() {
        List<FoodItem> items = restTemplate.getForObject(foodItemsUrl + "items", List.class);
        return items;
    }

    public Booking createBooking(LocalDate bookingDate) {
        if (bookingDate.minusDays(2).isAfter(LocalDate.now())) {
            throw new RuntimeException("Booking can only be done 2 days in prior");
        }
        Booking booking = new Booking(UUID.randomUUID(), bookingDate, LocalDate.now(), null,null, null);
        return bookingRepository.save(booking);
    }

    public Booking createOrder(UUID bookingId, Integer[] items) {
        Map<String, Object> params = new HashMap<>();
        UriComponents comp = UriComponentsBuilder.fromHttpUrl(
                foodOrdersUrl + "orders").queryParam("itemIds", items).build();
        ResponseEntity<UUID> response = restTemplate.postForEntity(comp.toString(), params, UUID.class);
        UUID orderId = response.getBody();
        Booking booking = bookingRepository.getById(bookingId);
        booking.setOrderId(orderId);
        return bookingRepository.save(booking);
    }

    public Float getBill(UUID bookingId){
        Booking booking = bookingRepository.getById(bookingId);
        if(booking.getOrderId() == null) return 0F;
        return restTemplate.getForObject(foodOrdersUrl + "orders/bill/" + booking.getOrderId(), Float.class);
    }

    public Booking setPayment(UUID bookingId, PaymentMode paymentMode){
        Booking booking = bookingRepository.getById(bookingId);
        booking.setPaymentMode(paymentMode);
        return bookingRepository.save(booking);
    }

    public Booking setFeedback(UUID bookingId, String feedback){
        Booking booking = bookingRepository.getById(bookingId);
        booking.setFeedback(feedback);
        return bookingRepository.save(booking);
    }
}
