package com.client.rentmanagement.billing;

import com.client.rentmanagement.billing.dto.BillingSummary;
import com.client.rentmanagement.meter.entity.MeterReading;
import com.client.rentmanagement.meter.repository.MeterReadingRepository;
import com.client.rentmanagement.pricing.PricingPolicy;
import com.client.rentmanagement.pricing.PricingPolicyRepository;
import com.client.rentmanagement.tenant.TenantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.UUID;

@Service
public class BillingCalculationService {

    private final TenantRepository tenantRepository;
    private final PricingPolicyRepository pricingPolicyRepository;
    private final MeterReadingRepository meterReadingRepository;

    public BillingCalculationService(
            TenantRepository tenantRepository,
            PricingPolicyRepository pricingPolicyRepository,
            MeterReadingRepository meterReadingRepository
    ) {
        this.tenantRepository = tenantRepository;
        this.pricingPolicyRepository = pricingPolicyRepository;
        this.meterReadingRepository = meterReadingRepository;
    }

    public BillingSummary calculateMonthlyBill(UUID tenantId) {

        MeterReading reading = meterReadingRepository
                .findTopByTenantIdOrderByReadingMonthDesc(tenantId)
                .orElseThrow(() -> new IllegalStateException("Meter reading not found"));

        PricingPolicy pricing = pricingPolicyRepository
                .findTopByActiveTrueOrderByEffectiveFromDesc()
                .orElseThrow(() -> new IllegalStateException("Pricing policy missing"));

        long unitsConsumed =
                reading.getCurrentReading() - reading.getPreviousReading();

        BigDecimal electricityCharge =
                pricing.getUnitPrice()
                        .multiply(BigDecimal.valueOf(unitsConsumed));

        long activeTenants = tenantRepository.countByActiveTrue();

        BigDecimal commonAreaCharge =
                pricing.getCommonAreaUnit()
                        .multiply(pricing.getUnitPrice())
                        .divide(BigDecimal.valueOf(activeTenants), 2, RoundingMode.HALF_UP);

        BigDecimal total =
                pricing.getRoomRent()
                        .add(electricityCharge)
                        .add(commonAreaCharge);

        BillingSummary summary = new BillingSummary();
        summary.setTenantId(tenantId);
        summary.setMonth(YearMonth.from(reading.getReadingMonth()));
        summary.setRoomRent(pricing.getRoomRent());
        summary.setUnitPrice(pricing.getUnitPrice());
        summary.setUnitsConsumed(unitsConsumed);
        summary.setElectricityCharge(electricityCharge);
        summary.setCommonAreaCharge(commonAreaCharge);
        summary.setTotalAmount(total);

        return summary;
    }
}
