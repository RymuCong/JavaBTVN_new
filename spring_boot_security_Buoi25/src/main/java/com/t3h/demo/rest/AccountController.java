package com.t3h.demo.rest;

import com.t3h.demo.AccountRequest.AccountAddRequest;
import com.t3h.demo.entity.Account;
import com.t3h.demo.repository.AccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("account")
    public ResponseEntity <?> getAll ()
    {
        log.info("Get all accounts data:");
        try {
            List<Account> accounts = accountRepo.findAll();
            if (accounts.size() == 0)
                return new ResponseEntity<>("No account on this list",HttpStatus.OK);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e)
        {
            log.error(e.toString());
        }
        return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("account/{id}")
    public ResponseEntity <?> getAccountById (@PathVariable Integer id)
    {
        log.info("Get student data:");
        try {
            Account account = accountRepo.findById(id).orElse(null);
            if (account == null)
                return new ResponseEntity<>("Can find the account with id: "+ id, HttpStatus.OK);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e)
        {
            log.error(e.toString());
        }
        return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("account")
    public ResponseEntity <?> add (@RequestBody AccountAddRequest accountReq)
    {
        log.info("Add new account");
        Account account = new Account();
        try {
            account.setFullName(accountReq.getFullName());
            account.setAddress(accountReq.getAddress());
            account.setEmail(accountReq.getEmail());
            accountRepo.save(account);
        } catch (Exception e)
        {
            log.error(e.toString());
        }
        return new ResponseEntity<>("Add successfully\n" + account, HttpStatus.OK);
    }

    @PutMapping("account/{id}")
    public ResponseEntity <?> update (@RequestBody AccountAddRequest accountReq, @PathVariable Integer id)
    {
        Account account = accountRepo.findById(id).orElse(null);
        if (account == null)
            return new ResponseEntity<>("Can find the account with id: "+ id, HttpStatus.OK);
        try {
            account.setFullName(accountReq.getFullName());
            account.setAddress(accountReq.getAddress());
            account.setEmail(accountReq.getEmail());
            accountRepo.save(account);
        } catch (Exception e)
        {
            log.error(e.toString());
        }
        return new ResponseEntity<>("Update successfully\n" + account, HttpStatus.OK);
    }

    @DeleteMapping("account/{id}")
    public ResponseEntity <?> delete (@PathVariable Integer id)
    {
        Account account = accountRepo.findById(id).orElse(null);
        if (account == null)
            return new ResponseEntity<>("Can find the account with id: "+ id, HttpStatus.OK);
        accountRepo.delete(account);
        return new ResponseEntity<>("Deleted successfully the account id: " + id, HttpStatus.OK);
    }
}
