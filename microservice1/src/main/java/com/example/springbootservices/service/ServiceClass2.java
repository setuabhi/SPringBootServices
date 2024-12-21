package com.example.springbootservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xx")
public class ServiceClass2 {

    @Autowired
    ServiceClass serviceClass;

    public ServiceClass2()
    {
        System.out.println("Service2 class object Created");
    }

    public String processMessage2(String message) {
        return serviceClass.processMessage(message);
    }
}
