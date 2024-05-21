package com.unknown.delivery.domain.restaurant.dto;

import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantResponse {
    private Long id;
    private String name;

    public static RestaurantResponse of(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .build();
    }
}
