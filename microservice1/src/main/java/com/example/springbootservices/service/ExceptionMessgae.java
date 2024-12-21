package com.example.springbootservices.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

public class ExceptionMessgae {
    String statusCode;
    String messgae;
    Time currentTime;

    public ExceptionMessgae(String statusCode, String messgae) {
        this.statusCode = statusCode;
        this.messgae = messgae;
        this.currentTime = Time.valueOf(LocalTime.now());
    }
}
