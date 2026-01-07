package com.client.rentmanagement.billing.repository;

import com.client.rentmanagement.billing.entity.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeterReadingRepository extends JpaRepository<MeterReading, UUID> {
}
