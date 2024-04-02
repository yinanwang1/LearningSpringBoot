package com.yn.learningspringboot.mapper;

import com.yn.learningspringboot.bean.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    public Account getAccount(String id);
}
