package com.example.springbootservices.controller;

import com.example.springbootservices.repository.Address;
import com.example.springbootservices.repository.AddressRepository;
import com.example.springbootservices.repository.Employee;
import com.example.springbootservices.repository.EmployeeRepository;
import com.example.springbootservices.service.ServiceClass2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    ServiceClass2 serviceClass2; //This is DI, I am not creating object, spring creating it in it's IOC container

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRepository addressRepository;

    //{
    //    "name": "Kanchani",
    //    "designation": "Software Engineer",
    //    "salary": 75000,
    //    "address": {
    //    "street": "Ghazhipur",
    //    "city": "Motihari"
    //  }
    //}
    @PostMapping("/saveEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new RuntimeException("Employee name cannot be empty"); // We will get 400 Bad request due to Controller Advise
        }
        return employeeRepository.save(employee);
    }

    @GetMapping("/allEmployee")
    List<Employee> allUser() {
        return employeeRepository.findAll();
    }

    @GetMapping("/getByIdAndName/{id}/{name}")
    List<Employee> allUser(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return employeeRepository.findByIdAndName(Long.valueOf(id), name);
    }

    @GetMapping("/deleteAll")
    String deleteAllUser() {
        employeeRepository.deleteAllData();
        return "All Data Removed";
    }

    @GetMapping("/transactionManagementDemo")
    @Transactional
    String transactionManagement() {
        employeeRepository.save(new Employee("Abhi", "NNN", 10L));

        saveAddress(new Address("Stree", "Motihari"));

        return "Inserted E,ployee and Address";
    }

    @Transactional
    private void saveAddress(Address address) {
        addressRepository.save(address);
    }

}
