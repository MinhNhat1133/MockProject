package com.vti.services;

import com.vti.entities.Service;
import com.vti.entities.ServiceCompletionProgress;

import java.util.List;

public interface ServiceCompletion {
    List<ServiceCompletionProgress> getServicesByOrderId(int orderId);

}
