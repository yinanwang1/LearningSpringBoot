package com.example.customer.mapper;

import com.example.customer.bean.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    public Account getAccount(String id);
}
