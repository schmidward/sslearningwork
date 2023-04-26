package com.ericdschmid.SecurityBackendApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping("/notices")
    public String getNotices() {
        System.out.println("\n*** USER GET request for notices details");
        return "Here are the notice details from the DB";
    }
}
