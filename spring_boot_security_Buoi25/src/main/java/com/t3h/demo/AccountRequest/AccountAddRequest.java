package com.t3h.demo.AccountRequest;

import lombok.Data;

@Data
public class AccountAddRequest {
    private String fullName;

    private String address;

    private String email;
}
