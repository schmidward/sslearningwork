package com.ericdschmid.SecurityBackendApplication.repository;

import com.ericdschmid.SecurityBackendApplication.model.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepository extends CrudRepository<Customers,Long> {

    //This says to find the details by a select query for email
    List<Customers> findByEmail(String email);

}
