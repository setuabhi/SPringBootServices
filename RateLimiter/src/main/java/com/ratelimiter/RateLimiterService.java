package com.ratelimiter;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private static final long TIME_WINDOW_MS = 20000L; // 20 seconds
    private static final int MAX_REQUESTS = 2;

    private final Map<String, Integer> requestCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRunTimeInMls = new ConcurrentHashMap<>();

    public synchronized boolean isAllowed(String userId) {

        long currentTime = System.currentTimeMillis();
        long windowStart = lastRunTimeInMls.getOrDefault(userId, 0L); //1st run it will be 0

        // Start new window if time has passed
        if (currentTime - windowStart >= TIME_WINDOW_MS) { // will always execute for 1st run
            lastRunTimeInMls.put(userId, currentTime); // update new window
            requestCounts.put(userId, 1);             // reset count
            return true;
        }

       int currentCount = requestCounts.get(userId); // for second run it will be 1

        if (currentCount < MAX_REQUESTS) {
            requestCounts.put(userId, currentCount + 1); // increase count
            return true;
        }

        return false; // limit exceeded
    }
}
