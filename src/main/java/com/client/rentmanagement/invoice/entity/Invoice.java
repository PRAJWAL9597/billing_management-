package com.client.rentmanagement.invoice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "invoice",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_invoice_tenant_month",
                        columnNames = {"tenant_id", "billing_month"}
                )
        }
)
public class Invoice {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "billing_month", nullable = false)
    private LocalDate billingMonth;

    @Column(name = "room_rent", nullable = false, precision = 10, scale = 2)
    private BigDecimal roomRent;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "units_consumed", nullable = false)
    private long unitsConsumed;

    @Column(name = "electricity_charge", nullable = false, precision = 10, scale = 2)
    private BigDecimal electricityCharge;

    @Column(name = "common_area_charge", nullable = false, precision = 10, scale = 2)
    private BigDecimal commonAreaCharge;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // ------------------------------------------------------------------
    // JPA requires a no-arg constructor
    // ------------------------------------------------------------------
    public Invoice() {
    }

    // ------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------

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

    // ------------------------------------------------------------------
    // Setters (used by InvoiceService only)
    // ------------------------------------------------------------------

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public void setBillingMonth(LocalDate billingMonth) {
        this.billingMonth = billingMonth;
    }

    public void setRoomRent(BigDecimal roomRent) {
        this.roomRent = roomRent;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitsConsumed(long unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public void setElectricityCharge(BigDecimal electricityCharge) {
        this.electricityCharge = electricityCharge;
    }

    public void setCommonAreaCharge(BigDecimal commonAreaCharge) {
        this.commonAreaCharge = commonAreaCharge;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
