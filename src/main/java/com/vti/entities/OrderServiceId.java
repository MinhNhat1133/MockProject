package com.vti.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class OrderServiceId implements Serializable {
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "service_id")
    private short serviceId;

    // Constructor

    public OrderServiceId(int orderId, short serviceId) {
        this.orderId = orderId;
        this.serviceId = serviceId;
    }

    public OrderServiceId() {
    }
}
