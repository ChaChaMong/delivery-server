package com.unknown.delivery.domain.restaurant.dao;

import com.unknown.delivery.domain.restaurant.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantCustomRepository {
    List<Restaurant> findAllRestaurants();

    Optional<Restaurant> findByRestaurantId(Long restaurantId);
}
