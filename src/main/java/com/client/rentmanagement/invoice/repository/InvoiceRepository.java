package com.client.rentmanagement.invoice.repository;

import com.client.rentmanagement.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    List<Invoice> findByTenantIdOrderByBillingMonthDesc(UUID tenantId);

    boolean existsByTenantIdAndBillingMonth(UUID tenantId, LocalDate billingMonth);
}
