package com.unknown.delivery.domain.restaurant.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.unknown.delivery.domain.restaurant.entity.QRestaurant.restaurant;

@Repository
@RequiredArgsConstructor
public class RestaurantCustomRepositoryImpl implements RestaurantCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Restaurant> findAllRestaurants() {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(restaurant.deletedAt.isNull());

        return jpaQueryFactory
                .select(restaurant)
                .from(restaurant)
                .where(builder)
                .fetch();
    }
}
