package com.unknown.delivery.domain.restaurant.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Restaurant> findByRestaurantId(Long restaurantId) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(restaurant.id.eq(restaurantId))
                .and(restaurant.deletedAt.isNull());

        return Optional.ofNullable(jpaQueryFactory
                .select(restaurant)
                .from(restaurant)
                .where(builder)
                .fetchFirst());
    }
}
