package org.mircro2.service;

import com.example.springbootservices.repository.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/web")
public class RestController {
    @Autowired
    FeignImpl feign;

    @GetMapping("/getByIdAndName/{id}/{name}")
    public List<Employee> getData(@PathVariable("id") Integer id, @PathVariable("name") String name)
    {
       return feign.allUser(id,name);
    }

    @GetMapping("/getCurrentTime")
    public String getTime()
    {
        return LocalTime.now().toString();
    }
}
