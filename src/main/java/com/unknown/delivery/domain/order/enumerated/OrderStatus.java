package com.unknown.delivery.domain.order.enumerated;

/**
 * 주문 상태
 */
public enum OrderStatus {
    /**
     * 주문 완료
     */
    COMPLETED,
    /**
     * 배송 중
     */
    IN_TRANSIT,
    /**
     * 배송 완료
     */
    DELIVERED,
    /**
     * 주문 취소
     */
    CANCELLED
}
