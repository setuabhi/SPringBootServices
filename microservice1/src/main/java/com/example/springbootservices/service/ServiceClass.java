package com.example.springbootservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceClass {

    public ServiceClass()
    {
        System.out.println("Service class object Created");
    }

    public String processMessage(String message) {
        throw new RuntimeException("Test");
    }
}

