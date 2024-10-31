package com.t3h.demo.controller;

import com.t3h.demo.dto.ProductShowDTO;
import com.t3h.demo.entity.Product;
import com.t3h.demo.repository.CategoryRepository;
import com.t3h.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/product")
public class ProductController {
    private final ProductRepository productRepo;

    private final CategoryRepository categoryRepo;

    @Autowired
    public ProductController(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("")
    public String getAll(Model theModel, Pageable pageable) {
        // get all products config by pageable
        Page<Product> products = productRepo.findAll(pageable);

        List <Product> productList = products.toList();

        String page = "product_list";

        theModel.addAttribute("totalPage", products.getTotalPages());
        theModel.addAttribute("currentPage", products.getNumber());
        theModel.addAttribute("products", productList);
        theModel.addAttribute("page", page);
        return "admin_index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Product> tempProduct = productRepo.findById(id);
        if (tempProduct.isEmpty()) {
            System.out.println("Not found the id :" + id);
        } else
            productRepo.deleteById(id);
        return "redirect:/admin/product";
    }

    @GetMapping("addForm")
    public String showFormForAdd (Model theModel)
    {
        // create model attribute to the bind form data
        Product theProduct = new Product();

        theModel.addAttribute("page","admin-product-add");

        theModel.addAttribute("product", theProduct);

        theModel.addAttribute("categories",  categoryRepo.findAll());

        return "admin_index";
    }

    @PostMapping("save")
    public String save(@ModelAttribute Product product, @RequestParam("imageFile") MultipartFile file) throws IOException
    {
        // url save image
        String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

        // not updated product createdAt if it has already
        if (product.getCreatedAt() != null)
            product.setCreatedAt(new Date());

        product.setUpdatedAt(new Date());

        if (!file.isEmpty())
        {
            // url image
            product.setImage(file.getOriginalFilename());

            // luu anh vao /images/
//            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
//            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }

        // save the product
        productRepo.save(product);

        return "redirect:/admin/product";
    }

    @GetMapping("edit/{id}")
    public String edit (@PathVariable Integer id, Model model)
    {
        Optional <Product> editProduct = productRepo.findById(id);

        if (editProduct.isEmpty())
            System.out.println("Not found product id: " + id);

        Product product = editProduct.get();

        model.addAttribute("page", "admin-product-edit");

        model.addAttribute("product", product);

        model.addAttribute("categories",  categoryRepo.findAll());

        return "admin_index";
    }




//    @PostMapping("save")
//    public String save(@RequestParam String productName, @RequestParam MultipartFile image, @RequestParam Integer price)
//    {
//        String imageName = image.getOriginalFilename();
//
//        Product theProduct = new Product();
//        theProduct.setProductName(productName);
//        theProduct.setPrice(price);
//        theProduct.setImage(imageName);
//        productRepo.save(theProduct);
//
//
//
//        return "redirect:/admin/product";
//    }

}
