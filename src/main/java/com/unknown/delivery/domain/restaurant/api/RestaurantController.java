package com.unknown.delivery.domain.restaurant.api;

import com.unknown.delivery.domain.restaurant.application.RestaurantService;
import com.unknown.delivery.domain.restaurant.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getRestaurants() {
        List<RestaurantResponse> responses = this.restaurantService.getRestaurants();
        return ResponseEntity.ok().body(responses);
    }
}
