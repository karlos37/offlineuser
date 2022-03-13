package com.greatlearning.offlineuser.foodOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodOrder {
    private UUID id;
    Map<String, Integer> items;
    private LocalDate createdOn;
}
