package com.vti.services;

import com.vti.entities.Service;
import com.vti.entities.ServiceCompletionProgress;

import java.time.LocalDate;
import java.util.List;

public interface ServiceCompletion {
    List<ServiceCompletionProgress> getServicesByOrderId(int orderId);

    void addOrderAndService(Integer orderId, Integer serviceId, LocalDate proposedDate);
}
