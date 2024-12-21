package org.mircro2.service;

import com.example.springbootservices.repository.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class Microservice1Fallback implements FeignImpl {
    @Override
    public List<Employee> allUser(Integer id, String name) {
        List<Employee> output= new ArrayList<>();
        output.add(new Employee("Fallback","Fallback",0));
        return output;
    }
}

