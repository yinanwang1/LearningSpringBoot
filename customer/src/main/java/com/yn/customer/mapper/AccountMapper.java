package com.yn.customer.mapper;

import com.yn.customer.bean.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    public Account getAccount(String id);
}
