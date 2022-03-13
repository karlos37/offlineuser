package com.greatlearning.offlineuser.foodItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "${client.foodItem.url}")
@EnableFeignClients
public interface FoodItemHttpClient {

    @GetMapping("/items")
    List<FoodItem> getMenu();
}
