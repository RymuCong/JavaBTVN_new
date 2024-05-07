package com.t3h.demo.controller;

import com.t3h.demo.entity.Account;
import com.t3h.demo.repository.AccountRepository;
import com.t3h.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/account")
public class AccountController {

//    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepo;

    public AccountController(AccountService accountService, AccountRepository accountRepo) {
        this.accountService = accountService;
        this.accountRepo = accountRepo;
    }

    @GetMapping("")
    public String getAll(Model model)
    {
        List <Account> accounts = accountService.getAll();
        String page = "account_list";
        model.addAttribute("page",page);
        model.addAttribute("accounts",accounts);
        return "admin_index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id)
    {
        Optional <Account> deleAccount = accountRepo.findById(id);
        if (deleAccount.isEmpty())
        {
            System.out.println("Not found the id :" + id);
        }
        else
            accountRepo.deleteById(id);

        return "redirect:/admin/account";
    }
}
