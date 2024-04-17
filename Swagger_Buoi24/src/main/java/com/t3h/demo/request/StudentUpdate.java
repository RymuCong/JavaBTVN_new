package com.t3h.demo.request;

import lombok.Data;

@Data
public class StudentUpdate {
    private String fullName;

    private double gpa;

    private String birthday;

    private String gender;
}
