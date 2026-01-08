package com.client.rentmanagement.meter.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "meter_reading",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tenant_id", "reading_month"})
    }
)
public class MeterReading {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    // DATE column → LocalDate
    @Column(name = "reading_month", nullable = false)
    private LocalDate readingMonth;

    // BIGINT → long
    @Column(name = "previous_reading", nullable = false)
    private long previousReading;

    @Column(name = "current_reading", nullable = false)
    private long currentReading;

    // NUMERIC(10,2) → BigDecimal
    @Column(name = "units_consumed", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitsConsumed;

    // TIMESTAMP → LocalDateTime ✅
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /* ---------- getters ---------- */

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public LocalDate getReadingMonth() {
        return readingMonth;
    }

    public long getPreviousReading() {
        return previousReading;
    }

    public long getCurrentReading() {
        return currentReading;
    }

    public BigDecimal getUnitsConsumed() {
        return unitsConsumed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
