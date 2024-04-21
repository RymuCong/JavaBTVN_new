package com.t3h.demo.rest;

import com.t3h.demo.entity.Category;
import com.t3h.demo.entity.Product;
import com.t3h.demo.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("")
    public ResponseEntity <?> getAll ()
    {
        log.info("Get all categories");
        log.error("Get data failed");
        try {
            List <Category> categories = categoryRepo.findAll();
            System.out.println(categories);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception ex)
        {
            log.error(ex.toString());
        }
        return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity <?> getCategoryById (@PathVariable Integer id)
    {
        log.info("Get category data:");
        log.error("Get data failed");
        try {
            Category category = categoryRepo.findById(id).orElse(null);
            if (category == null)
                return new ResponseEntity<>("Can find the category with id: "+ id, HttpStatus.OK);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e)
        {
            log.error(e.toString());
        }
        return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public ResponseEntity <?> save (@RequestBody Category category)
    {
        Category categoryDb = categoryRepo.findCategoryByName(category.getName());
        if (categoryDb != null)
        {
            return new ResponseEntity<>("Category existing: " + categoryDb.getName(),HttpStatus.BAD_REQUEST);
        }
        try {
            categoryRepo.save(category);
        } catch (Exception e)
        {
            log.error(e.toString());
        }
        return new ResponseEntity<>("Add successfully\n" + category, HttpStatus.OK);
    }


    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Category category) {
        log.info("Update category info");
        if (category != null && category.getId() != null) {
            Category existingcategory = categoryRepo.findById(category.getId()).orElse(null);
            if (existingcategory != null) {
                categoryRepo.save(category);
                return new ResponseEntity<>("Updated success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found with id: " + category.getId(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Category or Category ID is null", HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category != null) {
            if (category.getProducts().isEmpty()) {
                categoryRepo.deleteById(id);
                return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot delete: Category has one or more products", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("find")
    public ResponseEntity <?> findByCategoryName (@RequestParam(required = false) String categoryName)
    {
        Category category = categoryRepo.findCategoryByName(categoryName);
        if (category != null)
            return new ResponseEntity<>("Find the category:\n" + category,HttpStatus.OK);
        return new ResponseEntity<>("Can not found the category with name: " + categoryName,HttpStatus.BAD_REQUEST);
    }
}
