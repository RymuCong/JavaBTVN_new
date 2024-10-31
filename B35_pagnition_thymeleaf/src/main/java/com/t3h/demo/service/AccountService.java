package com.t3h.demo.service;

import com.t3h.demo.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {

    // abstract method, public access modifier
    List <Account> getAll();
}
