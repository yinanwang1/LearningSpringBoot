package com.yn.customer.bean;

import com.yn.customer.annotation.InitSex;
import com.yn.customer.annotation.ValidateAge;
import lombok.Data;

/**
 * @author arthurwang
 */
@Data
public class User {
    private String username;

    @ValidateAge(min = 20, max =35, value =22)
    private int age;

    @InitSex(sex = InitSex.SEX_TYPE.MAN)
    private String sex;
}
