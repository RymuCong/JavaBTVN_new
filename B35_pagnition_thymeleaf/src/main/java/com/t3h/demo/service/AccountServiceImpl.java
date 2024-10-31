package com.t3h.demo.service;

import com.t3h.demo.entity.Account;
import com.t3h.demo.repository.AccountRepository;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public List<Account> getAll() {
        List <Account> accounts = accountRepo.findAll();
        return accounts;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findAccountByUsername(username);

        if (account == null)
            throw new UsernameNotFoundException("Account not found with name : " + username);

        return new User(username, account.getPassword(), Collections.emptyList());
    }
}
