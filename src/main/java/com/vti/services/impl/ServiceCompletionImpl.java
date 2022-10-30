package com.vti.services.impl;

import com.vti.entities.ServiceCompletionProgress;
import com.vti.repositories.ServiceCompletionRepository;
import com.vti.services.ServiceCompletion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class ServiceCompletionImpl implements ServiceCompletion {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ServiceCompletionRepository serviceCompletionRepository;

//    @Autowired
//    private ServiceCompletionRepository serviceCompletionRepository;

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    public List<ServiceCompletionProgress> getServicesByOrderId(int orderId) {
        return null;
    }

    @Override
    public void addOrderAndService(Integer orderId, Integer serviceId, LocalDate proposedDate) {
        serviceCompletionRepository.addOrderAndService(orderId, serviceId, proposedDate);
    };


//    @Override
//    public List<ServiceCompletionProgress> getServicesByOrderId(int orderId) {
//        System.out.println("ServiceCompletionProgress");
//        return serviceCompletionRepository.findByServiceIdOrderId(orderId);
//    }

}

