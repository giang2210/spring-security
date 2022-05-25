package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public  boolean register(Account account){
        account.setPasswordHash(passwordEncoder.encode(account.getPasswordHash()));
        account.setRoleId(1);
        account.setStatus(1);
        accountRepository.save(account);
        return true;

    }
}
