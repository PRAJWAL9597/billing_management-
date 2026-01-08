package com.client.rentmanagement.pricing;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pricing_policy")
public class PricingPolicy {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "room_rent", nullable = false)
    private BigDecimal roomRent;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "common_area_unit", nullable = false)
    private BigDecimal commonAreaUnit;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // getters only (immutable pricing is good)

    public BigDecimal getRoomRent() {
        return roomRent;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getCommonAreaUnit() {
        return commonAreaUnit;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public boolean isActive() {
        return active;
    }
}
