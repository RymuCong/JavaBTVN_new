package com.t3h.demo.controller;

import com.t3h.demo.entity.Account;
import com.t3h.demo.repository.AccountRepository;
import com.t3h.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final AccountService accountService;

    private final AccountRepository accountRepo;

    @Autowired
    public AccountController(AccountService accountService, AccountRepository accountRepo) {
        this.accountService = accountService;
        this.accountRepo = accountRepo;
    }

    @GetMapping("")
    public String getAll(Model model, Pageable pageable)
    {
//        List <Account> accounts = accountService.getAll();

        Page <Account> accounts = accountRepo.findAll(pageable);

        String page = "account_list";

        model.addAttribute("totalPage", accounts.getTotalPages());
        model.addAttribute("currentPage", accounts.getNumber());
        model.addAttribute("page",page);
        model.addAttribute("accounts",accounts.toList());
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
