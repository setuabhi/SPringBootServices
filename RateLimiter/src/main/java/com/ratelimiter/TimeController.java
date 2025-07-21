package com.ratelimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
public class TimeController {

    @Autowired
    private RateLimiterService rateLimiterService;

    @GetMapping("/time")
    public String getTime(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        if (!rateLimiterService.isAllowed(clientIp)) {
            return "Rate limit exceeded. Please try again later.";
        }
        return "Server Time: " + LocalDateTime.now();
    }
}