package com.client.rentmanagement.invoice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "billing_month", nullable = false)
    private LocalDate billingMonth;

    @Column(name = "room_rent", nullable = false)
    private BigDecimal roomRent;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "units_consumed", nullable = false)
    private long unitsConsumed;

    @Column(name = "electricity_charge", nullable = false)
    private BigDecimal electricityCharge;

    @Column(name = "common_area_charge", nullable = false)
    private BigDecimal commonAreaCharge;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Invoice() {
        // for JPA
    }

    public Invoice(
            UUID tenantId,
            LocalDate billingMonth,
            BigDecimal roomRent,
            BigDecimal unitPrice,
            long unitsConsumed,
            BigDecimal electricityCharge,
            BigDecimal commonAreaCharge,
            BigDecimal totalAmount
    ) {
        this.id = UUID.randomUUID();
        this.tenantId = tenantId;
        this.billingMonth = billingMonth;
        this.roomRent = roomRent;
        this.unitPrice = unitPrice;
        this.unitsConsumed = unitsConsumed;
        this.electricityCharge = electricityCharge;
        this.commonAreaCharge = commonAreaCharge;
        this.totalAmount = totalAmount;
        this.createdAt = LocalDateTime.now();
    }

    // getters only

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public LocalDate getBillingMonth() {
        return billingMonth;
    }

    public BigDecimal getRoomRent() {
        return roomRent;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public long getUnitsConsumed() {
        return unitsConsumed;
    }

    public BigDecimal getElectricityCharge() {
        return electricityCharge;
    }

    public BigDecimal getCommonAreaCharge() {
        return commonAreaCharge;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
