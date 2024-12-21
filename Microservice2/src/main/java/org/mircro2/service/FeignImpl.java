package org.mircro2.service;

import com.example.springbootservices.repository.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "MICROSERVICE1", fallback = Microservice1Fallback.class)
public interface FeignImpl {

    @GetMapping("/api/getByIdAndName/{id}/{name}")
    List<Employee> allUser(@PathVariable("id") Integer id, @PathVariable("name") String name);

}
