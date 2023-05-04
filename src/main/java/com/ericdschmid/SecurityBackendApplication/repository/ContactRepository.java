package com.ericdschmid.SecurityBackendApplication.repository;

import com.ericdschmid.SecurityBackendApplication.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
