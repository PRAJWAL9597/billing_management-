package com.client.rentmanagement.meter.repository;

import com.client.rentmanagement.meter.entity.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MeterReadingRepository
        extends JpaRepository<MeterReading, UUID> {

    Optional<MeterReading>
    findTopByTenantIdOrderByReadingMonthDesc(UUID tenantId);
}
