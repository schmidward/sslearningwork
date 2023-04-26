package com.ericdschmid.SecurityBackendApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/myCards")
    public String getCardsDetails() {
        System.out.println("\n*** USER GET request for cards details");
        return "Here are the cards details from the DB";
    }
}
