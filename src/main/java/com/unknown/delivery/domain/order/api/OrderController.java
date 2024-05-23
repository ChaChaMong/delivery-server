package com.unknown.delivery.domain.order.api;

import com.unknown.delivery.domain.order.application.OrderService;
import com.unknown.delivery.domain.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(
            @RequestParam(name = "restaurantId") Long restaurantId) {
        List<OrderResponse> responses = this.orderService.getOrders(restaurantId);
        return ResponseEntity.ok().body(responses);
    }
}
