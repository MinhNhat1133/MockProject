package com.vti.services;

import java.time.LocalDate;
import java.util.List;

import com.vti.entities.ServiceCompletionProgress;

public interface ServiceCompletion {
    List<ServiceCompletionProgress> getServicesByOrderId(int orderId);

    void addOrderAndService(Integer orderId, Integer serviceId, LocalDate proposedDate);

	List<ServiceCompletionProgress> findServicesByOrderId(int orderId);

	void setServiceStatusIsCompleteByOrderIdAndServiceIds(int id, List<Integer> ids);
}
