package com.unknown.delivery.domain.order.application;

import com.unknown.delivery.domain.order.dto.OrderRequest;
import com.unknown.delivery.domain.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
}
