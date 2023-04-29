package com.ericdschmid.SecurityBackendApplication.controller;

import com.ericdschmid.SecurityBackendApplication.model.Customers;
import com.ericdschmid.SecurityBackendApplication.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    CustomersRepository customersRepository;

    //Method name is registerUser and accepts a customers object in the request body
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customers customers) {
        System.out.println("\n ***USER POST request to add a user to the backend database");
        Customers savedCustomers = null;
        ResponseEntity response = null;
        try {
            //using the save method in the customers repository
            savedCustomers = customersRepository.save(customers);

            //If there is a primary key value over 0 then we will send back a message
            if (savedCustomers.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully created");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to " + ex.getMessage());
        }
        return response;
    }
}
