package com.t3h.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String productName;

    private Integer price;

    private Date createdAt;

    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
