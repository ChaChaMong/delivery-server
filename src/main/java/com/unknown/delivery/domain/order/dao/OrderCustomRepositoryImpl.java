package com.unknown.delivery.domain.order.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unknown.delivery.domain.order.entity.Order;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.unknown.delivery.domain.order.entity.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Order> findOrdersByRestaurant(Restaurant restaurant, Pageable pageable, LocalDate start, LocalDate end) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(order.restaurant.eq(restaurant))
                .and(order.deletedAt.isNull());

        if (start != null && end != null) {
            builder.and(order.createdAt.between(start.atStartOfDay(), end.atTime(23, 59, 59)));
        } else if (start == null && end != null) {
            builder.and(order.createdAt.before(end.atTime(23, 59, 59)));
        } else if (start != null) {
            builder.and(order.createdAt.after(start.atStartOfDay()));
        }

        List<Order> orders = jpaQueryFactory
                .select(order)
                .from(order)
                .where(builder)
                .orderBy(order.createdAt.desc())
                .orderBy(order.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> total = jpaQueryFactory
                .select(order.count())
                .from(order)
                .where(builder);

        return PageableExecutionUtils.getPage(orders, pageable, total::fetchOne);
    }
}
