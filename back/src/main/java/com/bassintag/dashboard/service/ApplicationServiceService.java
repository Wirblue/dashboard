package com.bassintag.dashboard.service;

import com.bassintag.dashboard.exception.NotFoundException;
import com.bassintag.dashboard.service.application.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * ApplicationServiceService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 02/10/2018
 */
@Service
public class ApplicationServiceService {

    private final IApplicationService[] services;

    @Autowired
    public ApplicationServiceService(IApplicationService[] services) {
        this.services = services;
    }

    public IApplicationService[] getApplicationServices() {
        return services;
    }

    public IApplicationService getServiceByName(String name) {
        Optional<IApplicationService> optional = Arrays.stream(services).filter(s -> s.getName().equals(name)).findFirst();
        if (!optional.isPresent()) {
            throw new NotFoundException("Cannot find any service with name: " + name);
        }
        return optional.get();
    }

}
