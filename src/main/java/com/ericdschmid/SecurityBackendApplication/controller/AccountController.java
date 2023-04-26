package com.ericdschmid.SecurityBackendApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public String getAccountDetails() {
        System.out.println("\n*** USER GET request for account details");
        return "Here are the account details from the DB";
    }

}
