package com.example.springbootservices.controller;

import com.example.springbootservices.repository.Address;
import com.example.springbootservices.repository.Employee;
import com.example.springbootservices.repository.EmployeeRepository;
import com.example.springbootservices.service.ServiceClass2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    ServiceClass2 serviceClass2; //This is DI, I am not creating object, spring creating it in it's IOC container

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/saveEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new RuntimeException("Employee name cannot be empty");
        }
        return employeeRepository.save(employee);
    }

    @GetMapping("/allEmployee")
    List<Employee> allUser()

    {
        return employeeRepository.findAll();
    }

    @GetMapping("/getByIdAndName/{id}/{name}")
    List<Employee> allUser(@PathVariable("id") Integer id, @PathVariable("name") String name)
    {
        return employeeRepository.findByIdAndName(Long.valueOf(id),name);
    }

    @GetMapping("/deleteAll")
    String deleteAllUser()
    {
        employeeRepository.deleteAllData();
        return "All Data Removed";
    }

}
