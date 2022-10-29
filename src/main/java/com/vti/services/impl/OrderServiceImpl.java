package com.vti.services.impl;

import com.vti.entities.Order;
import com.vti.repositories.OrderRepository;
import com.vti.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findActiveOrderByCustomerId(int id) {
        return orderRepository.findActiveOrderByCustomerId(id);
    }

//    @Override
//    public void updatePaymentByOrderId(int id, String paymentStatus) {
//        orderRepository.updatePaymentByOrderId(id, paymentStatus);
//    }

}
