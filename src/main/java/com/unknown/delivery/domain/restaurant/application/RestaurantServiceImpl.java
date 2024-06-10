package com.unknown.delivery.domain.restaurant.application;

import com.unknown.delivery.domain.order.dao.OrderRepository;
import com.unknown.delivery.domain.order.dto.OrderResponse;
import com.unknown.delivery.domain.order.entity.Order;
import com.unknown.delivery.domain.restaurant.dao.RestaurantRepository;
import com.unknown.delivery.domain.restaurant.dto.RestaurantResponse;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import com.unknown.delivery.global.exception.BusinessException;
import com.unknown.delivery.global.response.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<RestaurantResponse> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAllRestaurants();

        return restaurants.stream()
                .map(RestaurantResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderResponse> getRestaurantOrders(Long id, int page, int size, LocalDate start, LocalDate end) {
        Restaurant restaurant = restaurantRepository.findByRestaurantId(id)
                .orElseThrow(() -> new BusinessException(HttpResponse.Fail.NOT_FOUND_RESTAURANT));

        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderRepository.findOrdersByRestaurant(restaurant, pageable, start, end);

        return orders.map(OrderResponse::of);
    }
}
