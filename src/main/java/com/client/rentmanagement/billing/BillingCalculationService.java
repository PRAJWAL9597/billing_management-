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
import java.time.LocalDate;
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

        // 1️⃣ Fetch latest meter reading
        MeterReading reading = meterReadingRepository
                .findTopByTenantIdOrderByReadingMonthDesc(tenantId)
                .orElseThrow(() ->
                        new IllegalStateException("Meter reading not found for tenant")
                );

        // 2️⃣ Fetch active pricing policy
        PricingPolicy pricing = pricingPolicyRepository
                .findTopByActiveTrueOrderByEffectiveFromDesc()
                .orElseThrow(() ->
                        new IllegalStateException("Active pricing policy not found")
                );

        // 3️⃣ Calculate units consumed
        long unitsConsumed =
                reading.getCurrentReading() - reading.getPreviousReading();

        if (unitsConsumed < 0) {
            throw new IllegalStateException(
                    "Current meter reading cannot be less than previous reading"
            );
        }

        // 4️⃣ Electricity charge
        BigDecimal electricityCharge =
                pricing.getUnitPrice()
                        .multiply(BigDecimal.valueOf(unitsConsumed));

        // 5️⃣ Common area charge split
        long activeTenants = tenantRepository.countByActiveTrue();

        if (activeTenants == 0) {
            throw new IllegalStateException("No active tenants found");
        }

        BigDecimal commonAreaCharge =
                pricing.getCommonAreaUnit()
                        .multiply(pricing.getUnitPrice())
                        .divide(
                                BigDecimal.valueOf(activeTenants),
                                2,
                                RoundingMode.HALF_UP
                        );

        // 6️⃣ Total amount
        BigDecimal totalAmount =
                pricing.getRoomRent()
                        .add(electricityCharge)
                        .add(commonAreaCharge);

        // 7️⃣ Build billing summary DTO
        BillingSummary summary = new BillingSummary();
        summary.setTenantId(tenantId);

        LocalDate readingDate = reading.getReadingMonth();
        summary.setMonth(
                YearMonth.of(
                        readingDate.getYear(),
                        readingDate.getMonth()
                )
        );

        summary.setRoomRent(pricing.getRoomRent());
        summary.setUnitPrice(pricing.getUnitPrice());
        summary.setUnitsConsumed(unitsConsumed);
        summary.setElectricityCharge(electricityCharge);
        summary.setCommonAreaCharge(commonAreaCharge);
        summary.setTotalAmount(totalAmount);

        return summary;
    }
}
