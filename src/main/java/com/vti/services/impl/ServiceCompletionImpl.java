package com.vti.services.impl;

import com.vti.entities.Service;
import com.vti.entities.ServiceCompletionProgress;
import com.vti.repositories.ServiceCompletionRepository;
import com.vti.services.ServiceCompletion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceCompletionImpl implements ServiceCompletion {
    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private ServiceCompletionRepository serviceCompletionRepository;

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    public List<ServiceCompletionProgress> getServicesByOrderId(int orderId) {
        return null;
    }

//    @Override
//    public List<ServiceCompletionProgress> getServicesByOrderId(int orderId) {
//        System.out.println("ServiceCompletionProgress");
//        return serviceCompletionRepository.findByServiceIdOrderId(orderId);
//    }

}

