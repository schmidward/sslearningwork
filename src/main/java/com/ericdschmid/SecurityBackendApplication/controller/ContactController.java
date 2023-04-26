package com.ericdschmid.SecurityBackendApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public String renderContactInquiryDetails()  {
        System.out.println("\n*** USER GET request for contact form");
        return "Inquiry details are saved into the DB";
    }
}
