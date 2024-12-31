package com.yn.customer.bean;

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

}
