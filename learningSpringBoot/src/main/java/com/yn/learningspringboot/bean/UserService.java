package com.yn.learningspringboot.bean;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.ZoneId;
import java.util.Locale;

@Log4j2
@Component
public class UserService {
    public final ZoneId zoneId = ZoneId.systemDefault();

    public UserService() {
        log.info("UserService(): init...");
        log.info("UserService(): zoneId = {}", zoneId);
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public final ZoneId getFinalZoneId() {
        return zoneId;
    }

    public static void main(String[] args) {
        double price = 123.5;
        int number = 10;
        Object[] arguments = {price, number};
        MessageFormat messageFormat = new MessageFormat("Pay {0, number, currency} for {1} books.", Locale.US);
        System.out.println(messageFormat.format(arguments));
        MessageFormat formatZH = new MessageFormat("{1}本书一共{0, number, currency}。", Locale.CHINA);
        System.out.println(formatZH.format(arguments));
    }

}
