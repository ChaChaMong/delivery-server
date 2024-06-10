package com.unknown.delivery.domain.order.dao;

import com.unknown.delivery.domain.order.entity.Order;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OrderCustomRepository {
    Page<Order> findOrdersByRestaurant(Restaurant restaurant, Pageable pageable, LocalDate start, LocalDate end);
}
