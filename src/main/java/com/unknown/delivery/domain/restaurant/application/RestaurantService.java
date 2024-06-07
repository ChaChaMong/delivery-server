package com.unknown.delivery.domain.restaurant.application;

import com.unknown.delivery.domain.order.dto.OrderResponse;
import com.unknown.delivery.domain.restaurant.dto.RestaurantResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getRestaurants();

    Page<OrderResponse> getRestaurantOrders(Long id, int page, int size);
}
