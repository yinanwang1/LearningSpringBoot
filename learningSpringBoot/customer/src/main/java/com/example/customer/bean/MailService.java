package com.example.customer.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class MailService {
    @Autowired
    UserService userService;

    public String sendMail() {
        ZoneId zoneId = userService.getFinalZoneId();
        String dt = ZonedDateTime.now(zoneId).toString();

        return "Hello, it is " + dt;
    }
}
