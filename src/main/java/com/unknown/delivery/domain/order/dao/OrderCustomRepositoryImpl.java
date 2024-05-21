package com.unknown.delivery.domain.order.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unknown.delivery.domain.order.entity.Order;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.unknown.delivery.domain.order.entity.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Order> findOrdersByRestaurant(Restaurant restaurant) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(order.restaurant.eq(restaurant))
                .and(order.deletedAt.isNull());

        return jpaQueryFactory
                .select(order)
                .from(order)
                .where(builder)
                .orderBy(order.id.desc())
                .fetch();
    }
}
