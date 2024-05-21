package com.unknown.delivery.domain.restaurant.dao;

import com.unknown.delivery.domain.restaurant.entity.Restaurant;

import java.util.List;

public interface RestaurantCustomRepository {
    List<Restaurant> findAllRestaurants();
}
