package com.unknown.delivery.domain.restaurant.application;

import com.unknown.delivery.domain.order.dto.OrderResponse;
import com.unknown.delivery.domain.restaurant.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getRestaurants();

    List<OrderResponse> getRestaurantOrders(Long id);
}
