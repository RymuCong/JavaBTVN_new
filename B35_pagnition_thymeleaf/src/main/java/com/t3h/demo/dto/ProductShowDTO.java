package com.t3h.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductShowDTO {

    private Integer id;

    private String productName;

    private Integer price;

    private String image;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private String categoryName;
}
