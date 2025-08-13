package com.yn.customer.bean;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car implements Serializable {
    @Serial
    private static final long serialVersionUID = -4915931152288524152L;

    public String name;
    public String price;
    private String color;
    private Integer weight;

    public void shout() {
        log.info("{} is running at {}", name, new DateTime());
    }

    public void add(int a, int b) {
        int c = a + b;
        log.info("{} + {} = {}", a, b, c);
    }
}


