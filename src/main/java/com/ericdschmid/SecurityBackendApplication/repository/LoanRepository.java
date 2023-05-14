package com.ericdschmid.SecurityBackendApplication.repository;

import com.ericdschmid.SecurityBackendApplication.model.Loans;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loans, Long> {
    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
