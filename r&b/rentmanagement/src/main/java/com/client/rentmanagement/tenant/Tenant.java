package com.client.rentmanagement.tenant;

import com.client.rentmanagement.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tenant")
public class Tenant extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "room_no", nullable = false, length = 20)
    private String roomNo;

    @Column(name = "phone_no", nullable = false, length = 15)
    private String phoneNo;

    @Column(length = 100)
    private String email;

    @Column(name = "meter_id", nullable = false, length = 50)
    private String meterId;
}
