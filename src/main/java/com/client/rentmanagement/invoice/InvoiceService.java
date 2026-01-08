package com.client.rentmanagement.invoice;

import com.client.rentmanagement.billing.BillingCalculationService;
import com.client.rentmanagement.billing.dto.BillingSummary;
import com.client.rentmanagement.invoice.entity.Invoice;
import com.client.rentmanagement.invoice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final BillingCalculationService billingCalculationService;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            BillingCalculationService billingCalculationService
    ) {
        this.invoiceRepository = invoiceRepository;
        this.billingCalculationService = billingCalculationService;
    }

    public Invoice generateInvoice(UUID tenantId) {

        BillingSummary summary =
                billingCalculationService.calculateMonthlyBill(tenantId);

        Invoice invoice = new Invoice(); // ✅ JPA-friendly

        invoice.setId(UUID.randomUUID());
        invoice.setTenantId(summary.getTenantId());
        invoice.setBillingMonth(
                LocalDate.of(
                        summary.getMonth().getYear(),
                        summary.getMonth().getMonth(),
                        1
                )
        );

        invoice.setRoomRent(summary.getRoomRent());
        invoice.setUnitPrice(summary.getUnitPrice());
        invoice.setUnitsConsumed(summary.getUnitsConsumed());
        invoice.setElectricityCharge(summary.getElectricityCharge());
        invoice.setCommonAreaCharge(summary.getCommonAreaCharge());
        invoice.setTotalAmount(summary.getTotalAmount());

        invoice.setCreatedAt(LocalDateTime.now());

        return invoiceRepository.save(invoice);
    }
}
