package com.ericdschmid.SecurityBackendApplication.repository;

import com.ericdschmid.SecurityBackendApplication.model.Cards;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardsRepository extends CrudRepository<Cards, Long> {

    List<Cards> findByCustomerId(int customerId);
}
