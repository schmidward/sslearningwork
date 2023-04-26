package com.ericdschmid.SecurityBackendApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getBalanceDetails() {
        System.out.println("\n*** USER GET request for balance details");
        return "Here are the balance details from the DB";
    }

}
