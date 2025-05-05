package org.trading_journal.example.trading_journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trading_journal.example.trading_journal.model.Account;

public interface AccountRepository extends JpaRepository<Account,Integer>{
    
}