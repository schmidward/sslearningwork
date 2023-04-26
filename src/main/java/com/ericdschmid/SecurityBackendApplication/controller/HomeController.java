package com.ericdschmid.SecurityBackendApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String sayHello(){
        return "Welcome to this Spring Application with out Security";
    }
}
