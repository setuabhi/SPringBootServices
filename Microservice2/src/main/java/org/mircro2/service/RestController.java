package org.mircro2.service;

import com.example.springbootservices.repository.Employee;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/web")
public class RestController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FeignImpl feign;

    @Autowired
    Timeservice timeservice;

    @GetMapping("/getByIdAndName/{id}/{name}")
    public List<Employee> getData(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return feign.allUser(id, name);
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackResponse")
    @GetMapping("/allEmployee")
    List<Employee> allUser() {
        return restTemplate.getForObject("http://MICROSERVICE1/api/allEmployee", List.class); //Load balancer
    }

    public List<Employee> fallbackResponse(Exception e) {
        List<Employee> output= new ArrayList<>();
        output.add(new Employee("Fallback using template"+e.getMessage(),"Fallback",0));
        return output;
    }

    @GetMapping("/getCurrentTime")
    public String getTime() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        return timeservice.returnTime();
    }

    @GetMapping("/getCurrentTimeAsyncWay")
    public String getTimeAsync() {
        System.out.println(Thread.currentThread().getName());
        timeservice.slowMethodToSendEmail(); //Will be running in different thread
        return "Request received! Processing in background...";  // Return immediately
    }
}
