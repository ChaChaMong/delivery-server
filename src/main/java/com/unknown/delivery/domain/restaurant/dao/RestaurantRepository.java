package com.unknown.delivery.domain.restaurant.dao;

import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantCustomRepository{
}
