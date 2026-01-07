package com.client.rentmanagement.pricing;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PricingPolicyRepository extends JpaRepository<PricingPolicy, UUID> {
}
