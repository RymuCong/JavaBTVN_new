package com.t3h.demo.controller;

import com.t3h.demo.entity.Product;
import com.t3h.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("")
    public String getAll (Model theModel)
    {
        List <Product> products = productRepo.findAll();
        String page = "product_list";
        theModel.addAttribute("products",products);
        theModel.addAttribute("page", page);
        return "admin_index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id)
    {
        Optional<Product> tempProduct = productRepo.findById(id);
        if (tempProduct.isEmpty())
        {
            System.out.println("Not found the id :" + id);
        }
        else
            productRepo.deleteById(id);
        return "redirect:/admin/product";
    }
}
