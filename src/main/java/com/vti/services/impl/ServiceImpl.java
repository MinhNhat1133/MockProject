package com.vti.services.impl;

import com.vti.repositories.ServiceRepository;
import com.vti.services.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceImpl implements Service {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<com.vti.entities.Service> getAllRequiredServices() {
        return serviceRepository.findAllRequiredServices();
    }
}
