package com.client.rentmanagement.billing.dto;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

public class BillingSummary {

    private UUID tenantId;
    private YearMonth month;

    private BigDecimal roomRent;
    private BigDecimal unitPrice;

    private long unitsConsumed;
    private BigDecimal electricityCharge;
    private BigDecimal commonAreaCharge;

    private BigDecimal totalAmount;

    // setters only (DTO)

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
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
}
