package com.yn.customer.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "person.pet")
@Component
@Data
public class Pet {
    private String name;
    private Integer age;

    private Double weight;
}
