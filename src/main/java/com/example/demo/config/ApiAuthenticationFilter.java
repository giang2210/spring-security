package com.example.demo.config;

import com.example.demo.entity.Account;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
            Account account = gson.fromJson(jsonData, Account.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    account.getUsername(), account.getPasswordHash()); // tao ra thong tin check dang nhap.
            return authenticationManager.authenticate(token);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
