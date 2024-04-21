package com.t3h.demo.rest;

import com.t3h.demo.entity.Account;
import com.t3h.demo.repository.ProductRepository;
import com.t3h.demo.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("")
    public ResponseEntity <?> getAll ()
    {
        log.info("Get all products");
        log.error("Get data failed");

        try {
            List <Product> products = productRepo.findAll();
            System.out.println(products);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception ex)
        {
            log.error(ex.toString());
        }
        return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity <?> getAccountById (@PathVariable Integer id)
    {
        log.info("Get product data:");
        log.error("Get data failed");
        try {
           Product product = productRepo.findById(id).orElse(null);
            if (product == null)
                return new ResponseEntity<>("Can find the product with id: "+ id, HttpStatus.OK);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e)
        {
            log.error(e.toString());
        }
        return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public ResponseEntity <?> save (@RequestBody Product product)
    {
        try {
            productRepo.save(product);
        } catch (Exception e)
        {
            log.error(e.toString());
        }
        return new ResponseEntity<>("Add successfully\n" + product, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Product product) {
        log.info("Update product info");
        if (product != null && product.getId() != null) {
            Product existingProduct = productRepo.findById(product.getId()).orElse(null);
            if (existingProduct != null) {
                productRepo.save(product);
                return new ResponseEntity<>("Updated success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found with id: " + product.getId(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Product or Product ID is null", HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("{id}")
    public ResponseEntity <?> delete (@PathVariable Integer id)
    {
        Product product = productRepo.findById(id).orElse(null);
        if (product != null)
        {
            productRepo.deleteById(id);
            return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Can not found the product id: " + id,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("find")
    public ResponseEntity <?> findByproductName (@RequestParam(required = false) String productName)
    {
        Product product = productRepo.findProductByProductName(productName);
        if (product != null)
            return new ResponseEntity<>("Find the product:\n" + product,HttpStatus.OK);
        return new ResponseEntity<>("Can not found the product with name: " + productName,HttpStatus.BAD_REQUEST);
    }
}
