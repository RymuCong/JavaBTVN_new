package com.t3h.demo.controller;

import com.t3h.demo.entity.Category;
import com.t3h.demo.entity.Product;
import com.t3h.demo.repository.CategoryRepository;
import com.t3h.demo.repository.ProductRepository;
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
@RequestMapping("/admin/category")
public class CategoryController {
    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    @Autowired
    public CategoryController(CategoryRepository categoryRepo, ProductRepository productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @GetMapping("")
    public String getAll (Model model, Pageable pageable)
    {
//        List <Category> categories = categoryRepo.findAll();
        Page<Category> categories = categoryRepo.findAll(pageable);

        String page = "categories_list";

        model.addAttribute("totalPage", categories.getTotalPages());
        model.addAttribute("currentPage", categories.getNumber());
        model.addAttribute("categories",categories.toList());
        model.addAttribute("page",page);
        return "admin_index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id)
    {
        Optional<Category> tempCategory = categoryRepo.findById(id);
        if (tempCategory.isEmpty())
        {
            System.out.println("Not found the id :" + id);
        }
        else
        {
            List<Product> products = productRepo.findByCategoryId(id);
            // Set the category of these products to null
            for (Product product : products) {
                product.setCategory(null);
                productRepo.save(product);
            }
            // Delete the category
            categoryRepo.deleteById(id);
        }

        return "redirect:/admin/category";
    }

}
