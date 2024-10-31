package com.t3h.demo.controller;

import com.t3h.demo.dto.ProductShowDTO;
import com.t3h.demo.entity.Product;
import com.t3h.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientIndexController {
    private final ProductRepository productRepo;

    @Autowired
    public ClientIndexController(ProductRepository productRepo)
    {
        this.productRepo = productRepo;
    }

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
    public String getAllProduct (Model theModel, Pageable pageable)
    {
        // List products + pageNumber + pageSize
        Page<Product> productsPage = productRepo.findAll(pageable);

        // get all products config by pageable
        // get list products from products
        List <Product> productList = productsPage.toList();

        List <ProductShowDTO> productShowDTOS = new ArrayList<>();

        productList.forEach(product -> {
            ProductShowDTO productShowDTO = new ProductShowDTO();
            productShowDTO.setId(product.getId());
            productShowDTO.setProductName(product.getProductName());
            productShowDTO.setDescription(product.getDescription());
            productShowDTO.setPrice(product.getPrice());
            productShowDTO.setImage(product.getImage());
            productShowDTO.setCreatedAt(product.getCreatedAt());
            productShowDTO.setUpdatedAt(product.getUpdatedAt());

            if (product.getCategory() != null)
                productShowDTO.setCategoryName(product.getCategory().getCategoryName());

            productShowDTOS.add(productShowDTO);
        });

        String page = "product_list_client";
        theModel.addAttribute("products", productShowDTOS);
        theModel.addAttribute("page", page);
        return "client_index";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Integer id, Model model) {
        Optional<Product> OpProduct = productRepo.findById(id);

        Product product = OpProduct.get();

        String page = "product_detail_client";
        model.addAttribute("page",page);

        model.addAttribute("product", product);
        return "client_index"; // return the view name
    }

    @GetMapping("search")
    public String searchProduct (Model model, @RequestParam(required = false) String data)
    {
        String page = "product_search";
        model.addAttribute("page",page);

        // if data is null, return all products

        List<Product> products;

        if (data == null || data.isEmpty() || data.trim().isEmpty())
        {
            products = productRepo.findAll();
            model.addAttribute("products",products);
        }
        else
            products = productRepo.findByProductNameContaining(data);

        model.addAttribute("products",products);
        return "client_index";
    }
}
