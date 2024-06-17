package com.yn.learningspringboot.bean;

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

    private String name;
    private String price;
    private String color;
}


