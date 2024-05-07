package com.t3h.demo.controller;

import com.t3h.demo.entity.Product;
import com.t3h.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClientIndexController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("")
    public String clientIndex (Model theModel)
    {
        String page = "client_index";
        theModel.addAttribute("page",page);
        return "client_index";
    }

    @GetMapping("contact")
    public String clientContact (Model model)
    {
        String page = "client_contact";

        model.addAttribute("page",page);

        return "client_index";
    }

    @GetMapping("admin")
    public String adminIndex (Model model)
    {
        String page = "admin_index";
        model.addAttribute("page",page);
        return "admin_index";
    }

    @GetMapping("product")
    public String getAllProduct (Model theModel)
    {
        List<Product> products = productRepo.findAll();
        String page = "product_list_client";
        theModel.addAttribute("products",products);
        theModel.addAttribute("page", page);
        return "client_index";
    }
}
