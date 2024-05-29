package com.unknown.delivery.domain.order.dto;

import com.unknown.delivery.domain.order.entity.Order;
import com.unknown.delivery.domain.order.enumerated.OrderStatus;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Builder
public class OrderRequest {
    private Map<String, Object> content;
    private OrderStatus status;
    private String address;
    private String addressDetail;
    private String postcode;
    private Long phone;
    private String ordererName;

    public static Order toEntity(OrderRequest request, Restaurant restaurant, BigDecimal totalPrice) {
        return Order.builder()
                .restaurant(restaurant)
                .content(request.getContent())
                .price(totalPrice)
                .status(request.getStatus())
                .address(request.getAddress())
                .addressDetail(request.getAddressDetail())
                .postcode(request.getPostcode())
                .phone(request.getPhone())
                .ordererName(request.getOrdererName())
                .build();
    }
}
