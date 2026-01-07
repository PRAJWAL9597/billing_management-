package com.client.rentmanagement.billing.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "meter_reading")
public class MeterReading {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "reading_month", nullable = false)
    private LocalDate readingMonth;

    @Column(name = "previous_reading", nullable = false)
    private BigDecimal previousReading;

    @Column(name = "current_reading", nullable = false)
    private BigDecimal currentReading;

    @Column(name = "units_consumed", nullable = false)
    private BigDecimal unitsConsumed;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.unitsConsumed = currentReading.subtract(previousReading);
    }

    // getters & setters
}
