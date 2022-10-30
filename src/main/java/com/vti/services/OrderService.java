package com.vti.services;

import com.vti.entities.Order;
import com.vti.forms.OrderUpdateForm;

public interface OrderService {
    Order findActiveOrderByCustomerId(int id);

    Order getOrderById(int id);

    void updateOrderById(int id, OrderUpdateForm orderUpdateForm);


//    void updatePaymentByOrderId(int id, String paymentStatus);
}
