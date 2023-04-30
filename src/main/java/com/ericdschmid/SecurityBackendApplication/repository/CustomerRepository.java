package com.ericdschmid.SecurityBackendApplication.repository;

import com.ericdschmid.SecurityBackendApplication.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    //This says to find the details by a select query for email
    List<Customer> findByEmail(String email);

}
