package com.vti.services;

import com.vti.entities.Order;

public interface OrderService {
    Order findActiveOrderByCustomerId(int id);

//    void updatePaymentByOrderId(int id, String paymentStatus);
}
