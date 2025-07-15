package com.yn.customer.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserProfile
 *
 * @author arthurwang
 * @version 1.0
 * 2025/6/23 11:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private  User user;
    private String location;
}
