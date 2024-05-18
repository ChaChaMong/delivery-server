package com.unknown.delivery.domain.order.entity;

import com.unknown.delivery.domain.order.enumerated.OrderStatus;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import com.unknown.delivery.global.entity.BaseEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {
    @Type(JsonType.class)
    @Column(name = "content", columnDefinition ="JSON")
    @Builder.Default
    private Map<String, Object> content = new HashMap<>();

    @Column(name = "price", columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal price;

    @Column(name = "status", columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "address", columnDefinition = "VARCHAR(200)")
    private String address;

    @Column(name = "address_detail", columnDefinition = "VARCHAR(200)")
    private String addressDetail;

    @Column(name = "postcode", columnDefinition = "VARCHAR(50)")
    private String postcode;

    @Column(name = "phone", columnDefinition = "BIGINT")
    private Long phone;

    @Column(name = "orderer_name", columnDefinition = "VARCHAR(200)")
    private String ordererName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Restaurant restaurant;
}
