package com.yn.learningspringboot.service;

import com.yn.learningspringboot.bean.Account;
import com.yn.learningspringboot.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public Account getAccountById(String id) {
        log.info("accountMapper is {}", accountMapper);
        return accountMapper.getAccount(id);
    }
}
