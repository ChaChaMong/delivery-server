package com.unknown.delivery.domain.order.application;

import com.unknown.delivery.domain.order.dao.OrderRepository;
import com.unknown.delivery.domain.order.dto.OrderResponse;
import com.unknown.delivery.domain.order.entity.Order;
import com.unknown.delivery.domain.restaurant.dao.RestaurantRepository;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import com.unknown.delivery.global.exception.BusinessException;
import com.unknown.delivery.global.response.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<OrderResponse> getOrders(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new BusinessException(HttpResponse.Fail.NOT_FOUND_RESTAURANT));

        List<Order> orders = orderRepository.findOrdersByRestaurant(restaurant);

        return orders.stream()
                .map(OrderResponse::of)
                .collect(Collectors.toList());
    }
}
