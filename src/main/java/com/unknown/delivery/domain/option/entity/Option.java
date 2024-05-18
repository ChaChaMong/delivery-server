package com.unknown.delivery.domain.option.entity;

import com.unknown.delivery.domain.menu.entity.Menu;
import com.unknown.delivery.domain.option.enumerated.OptionType;
import com.unknown.delivery.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "option")
public class Option extends BaseEntity {
    @Column(name = "name", columnDefinition = "VARCHAR(200)")
    private String name;

    @Column(name = "type", columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private OptionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Menu menu;
}
