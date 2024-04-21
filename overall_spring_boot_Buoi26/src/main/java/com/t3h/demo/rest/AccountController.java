package com.t3h.demo.rest;

import com.t3h.demo.entity.Account;
import com.t3h.demo.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountRepository accountRepo;

    @GetMapping("")
    public ResponseEntity <?> getAll ()
    {
        log.info("Get all account");
        log.error("Get data failed");

        try {
            List<Account> accounts = accountRepo.findAll();
            System.out.println(accounts);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception ex)
        {
            log.error(ex.toString());
        }
        return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity <?> getAccountById (@PathVariable Integer id)
    {
        log.info("Get account data:");
        log.error("Get data failed");
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

    @PostMapping("")
    public ResponseEntity <?> save (@RequestBody Account account)
    {
        String passwordBCrypt = new BCryptPasswordEncoder().encode(account.getPassword());
        account.setPassword(passwordBCrypt);
        Account accountDb = accountRepo.findAccountByUsername(account.getEmail());
        if (accountDb != null)
        {
            return new ResponseEntity<>("Email existing: " + accountDb.getEmail(),HttpStatus.BAD_REQUEST);
        }
        accountRepo.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }


    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Account account) {
        log.info("Update account info");
        if (account != null && account.getId() != null) {
            Account existingAccount = accountRepo.findById(account.getId()).orElse(null);
            if (existingAccount != null) {
                accountRepo.save(account);
                return new ResponseEntity<>("Updated success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found with id: " + account.getId(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Account or Account ID is null", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity <?> delete (@PathVariable Integer id)
    {
        Account account = accountRepo.findById(id).orElse(null);
        if (account != null)
        {
            accountRepo.deleteById(id);
            return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Can not found the account id: " + id,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("find")
    public ResponseEntity <?> findByAccountName (@RequestParam(required = false) String accountName)
    {
        Account account = accountRepo.findAccountByUsername(accountName);
        if (account != null)
            return new ResponseEntity<>("Find the account:\n" + account,HttpStatus.OK);
        return new ResponseEntity<>("Can not found the account with username: " + accountName,HttpStatus.BAD_REQUEST);
    }
}
