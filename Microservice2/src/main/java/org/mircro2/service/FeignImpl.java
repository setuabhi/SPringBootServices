package org.mircro2.service;

import com.example.springbootservices.repository.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@FeignClient(name = "MICROSERVICE1", fallback = Microservice1Fallback.class) //We did not give url so load balancer
public interface FeignImpl {

    @GetMapping("/api/getByIdAndName/{id}/{name}")
    List<Employee> allUser(@PathVariable("id") Integer id, @PathVariable("name") String name);


}
