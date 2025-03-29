package org.mircro2.service;

import com.example.springbootservices.repository.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/web")
public class RestController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FeignImpl feign;

    @GetMapping("/getByIdAndName/{id}/{name}")
    public List<Employee> getData(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return feign.allUser(id, name);
    }

    @GetMapping("/allEmployee")
    List<Employee> allUser() {
        return restTemplate.getForObject("http://MICROSERVICE1/api/allEmployee", List.class); //Load balancer
    }

    @GetMapping("/getCurrentTime")
    public String getTime() {
        return LocalTime.now().toString();
    }
}
