package com.unknown.delivery.domain.order.dao;

import com.unknown.delivery.domain.order.entity.Order;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;

import java.util.List;

public interface OrderCustomRepository {
    List<Order> findOrdersByRestaurant(Restaurant restaurant);
}
