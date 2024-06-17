package com.yn.learningspringboot.bean;

import com.yn.learningspringboot.annotation.InitSex;
import com.yn.learningspringboot.annotation.ValidateAge;
import lombok.Data;

@Data
public class User {
    private String username;

    @ValidateAge(min = 20, max =35, value =22)
    private int age;

    @InitSex(sex = InitSex.SEX_TYPE.MAN)
    private String sex;
}
