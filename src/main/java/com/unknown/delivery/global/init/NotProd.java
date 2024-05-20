package com.unknown.delivery.global.init;

import com.unknown.delivery.domain.restaurant.dao.RestaurantRepository;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final RestaurantRepository restaurantRepository;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            if (!restaurantRepository.findAll().isEmpty()) {
                return;
            }

            initRestaurant();
        };
    }

    private void initRestaurant() {
        restaurantRepository.save(Restaurant.builder().name("매장1").build());
        restaurantRepository.save(Restaurant.builder().name("매장2").build());
        restaurantRepository.save(Restaurant.builder().name("매장3").build());
    }
}
