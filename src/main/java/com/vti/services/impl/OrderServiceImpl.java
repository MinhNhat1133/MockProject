package com.vti.services.impl;

import com.vti.entities.Order;
import com.vti.forms.OrderUpdateForm;
import com.vti.repositories.OrderRepository;
import com.vti.services.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Order findActiveOrderByCustomerId(int id) {
        return orderRepository.findActiveOrderByCustomerId(id);
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void updateOrderById(int id, OrderUpdateForm orderUpdateForm) {
        Order oldOrder = getOrderById(id);
        Integer newPaymentStatus = orderUpdateForm.getPaymentStatus();
        if (newPaymentStatus != null)
            oldOrder.setPayment_status(orderUpdateForm.getPaymentStatus());
        orderRepository.save(oldOrder);
    }

//    @Override
//    public void updatePaymentByOrderId(int id, String paymentStatus) {
//        orderRepository.updatePaymentByOrderId(id, paymentStatus);
//    }

}
