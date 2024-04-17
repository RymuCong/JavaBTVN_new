package com.t3h.demo.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequest {

    private String fullName;

    private double gpa;

    private String birthday;

    private String gender;
}
