package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String Hello(){
        return "Hello User";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> register(@RequestBody Account account){
        accountService.register(account);

        return  null;
    }
}
