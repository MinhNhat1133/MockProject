package com.vti.controllers;

import com.vti.dtos.OrderDTO;
import com.vti.entities.Order;
import com.vti.forms.CustomerAndOrderCreateForm;
import com.vti.forms.OrderUpdateForm;
import com.vti.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<?> getActiveOrderByCustomerId(@RequestParam int customerId) {
        Order order = orderService.findActiveOrderByCustomerId(customerId);
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateOrderById(@PathVariable(name = "id" , required = true) int id, @RequestBody OrderUpdateForm newOrderForm) {
        orderService.updateOrderById(id, newOrderForm);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }
}
