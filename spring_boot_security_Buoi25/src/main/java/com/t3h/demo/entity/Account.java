package com.t3h.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="account")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String address;

    private String email;
}
