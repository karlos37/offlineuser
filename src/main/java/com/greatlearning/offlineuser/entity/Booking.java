package com.greatlearning.offlineuser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking {
    @Id
    private UUID bookingId;
    private LocalDate bookingDate;
    private LocalDate bookedOn;
    private UUID orderId;
    private String feedback;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
}
