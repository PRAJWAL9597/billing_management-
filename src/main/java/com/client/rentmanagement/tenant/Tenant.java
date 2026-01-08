package com.client.rentmanagement.tenant;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tenant")
public class Tenant {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "room_no", nullable = false)
    private String roomNo;

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    private String email;

    @Column(name = "meter_id", nullable = false)
    private String meterId;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /* ---------- getters ---------- */

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public String getMeterId() {
        return meterId;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
