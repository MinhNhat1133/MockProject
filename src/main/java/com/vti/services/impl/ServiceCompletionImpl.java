package com.vti.services.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entities.ServiceCompletionProgress;
import com.vti.repositories.OrderRepository;
import com.vti.repositories.ServiceCompletionRepository;
import com.vti.services.ServiceCompletion;

@Service
@Transactional
public class ServiceCompletionImpl implements ServiceCompletion {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ServiceCompletionRepository serviceCompletionRepository;

	@Override
	public List<ServiceCompletionProgress> getServicesByOrderId(int orderId) {
		return null;
	}

	@Override
	public void addOrderAndService(Integer orderId, Integer serviceId, LocalDate proposedDate) {
		serviceCompletionRepository.addOrderAndService(orderId, serviceId, proposedDate);
	}

	@Override
	public List<ServiceCompletionProgress> findServicesByOrderId(int orderId) {
		return this.serviceCompletionRepository.findServicesByOrderId(orderId);
	}

	@Override
	public void setServiceStatusIsCompleteByOrderIdAndServiceIds(int id, List<Integer> ids) {
		Integer check = serviceCompletionRepository.setServiceStatusIsCompleteByOrderIdAndServiceIds(id, ids);
		if (check > 0) {
			float weightServiceComplete = this.orderRepository.getWeightServiceCompleteByOrderId(id);
			float totalWeightService = this.orderRepository.getTotalWeightServiceByOrderId(id);
			float newStatus = (weightServiceComplete / totalWeightService) * 100;
			this.orderRepository.UpdateStatus(id, Math.round(newStatus));
		}
	}

}
