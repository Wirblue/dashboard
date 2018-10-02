package com.bassintag.dashboard.controller;

import com.bassintag.dashboard.dto.ServiceDto;
import com.bassintag.dashboard.exception.NotFoundException;
import com.bassintag.dashboard.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServicesController {

    private final ServiceDto[] services;

    @Autowired
    public ServicesController(IService[] services) {
        this.services = Arrays.stream(services).map(ServiceDto::new).toArray(ServiceDto[]::new);
    }

    @GetMapping
    public ServiceDto[] services() {
        return services;
    }

    private ServiceDto getServiceByName(String name) {
        Optional<ServiceDto> optional = Arrays.stream(services).filter(s -> s.getName().equals(name)).findFirst();
        if (!optional.isPresent()) {
            throw new NotFoundException("Cannot find any service with name: " + name);
        }
        return optional.get();
    }

    @GetMapping("/{name}")
    public ServiceDto service(@PathVariable String name) {
        return getServiceByName(name);
    }
}
