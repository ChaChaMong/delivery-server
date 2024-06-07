package com.unknown.delivery.domain.restaurant.api;

import com.unknown.delivery.domain.order.dto.OrderResponse;
import com.unknown.delivery.domain.restaurant.application.RestaurantService;
import com.unknown.delivery.domain.restaurant.dto.RestaurantResponse;
import com.unknown.delivery.global.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("{id}/orders")
    public ResponseEntity<PageResponse<OrderResponse>> getRestaurantOrders(
            @PathVariable("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        PageResponse<OrderResponse> responses = PageResponse.of(this.restaurantService.getRestaurantOrders(id, page, size));
        return ResponseEntity.ok().body(responses);
    }
}
