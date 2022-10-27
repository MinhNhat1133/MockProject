package com.vti.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderServicePK implements Serializable {
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "service_id")
    private short serviceId;

    // Constructor

    public OrderServicePK(int orderId, short serviceId) {
        this.orderId = orderId;
        this.serviceId = serviceId;
    }

    public OrderServicePK() {
    }
}
