package com.client.rentmanagement.pricing;

import com.client.rentmanagement.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pricing_policy")
public class PricingPolicy extends BaseEntity {

    @Column(name = "room_rent", nullable = false, precision = 10, scale = 2)
    private BigDecimal roomRent;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "common_area_unit", nullable = false, precision = 10, scale = 2)
    private BigDecimal commonAreaUnit;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;
}
