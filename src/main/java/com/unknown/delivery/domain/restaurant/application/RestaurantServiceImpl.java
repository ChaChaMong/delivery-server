package com.unknown.delivery.domain.restaurant.application;

import com.unknown.delivery.domain.restaurant.dao.RestaurantRepository;
import com.unknown.delivery.domain.restaurant.dto.RestaurantResponse;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantResponse> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAllRestaurants();

        return restaurants.stream()
                .map(RestaurantResponse::of)
                .collect(Collectors.toList());
    }
}
