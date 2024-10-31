package com.t3h.demo.repository;

import com.t3h.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {

    List <Product> findByCategoryId(Integer theId);

    List<Product> findByProductNameContaining(String data);

    Page <Product> findAll(Pageable pageable);
}
