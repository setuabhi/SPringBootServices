package org.mircro2.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

@Service
public class Timeservice {

    //// This method will be running in same thread where getTime() is running
    String returnTime() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(10000);
        return LocalTime.now().toString();
    }

    @Async  // Runs this method in a separate thread while getTimeAsync will be running in different thread
    void slowMethodToSendEmail() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10000);  // Simulating delay of 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email sent!");
    }
}
