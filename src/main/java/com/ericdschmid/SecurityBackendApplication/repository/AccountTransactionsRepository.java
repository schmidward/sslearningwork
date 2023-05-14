package com.ericdschmid.SecurityBackendApplication.repository;

import com.ericdschmid.SecurityBackendApplication.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, Long> {

    //This naming method forces Spring JPA to consider order by and the descending order
    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);
}
