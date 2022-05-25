package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

public class AuthenticationService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(username);
        if (!optionalAccount.isPresent()){
            throw  new UsernameNotFoundException("Invalid information");
        }
        Account account = optionalAccount.get();
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (account.getRoleId() == 1){
            authorities.add(new SimpleGrantedAuthority("user"));
        }else if (account.getRoleId() == 2){
            authorities.add(new SimpleGrantedAuthority("admin"));
        }
        UserDetails userDetails = new User(account.getUsername(), account.getPasswordHash(), authorities);
        return null;
    }
}
