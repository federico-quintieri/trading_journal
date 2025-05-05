package org.trading_journal.example.trading_journal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trading_journal.example.trading_journal.model.Account;
import org.trading_journal.example.trading_journal.repository.AccountRepository;

@Service
public class AccountService {

    // Autowired repo necessarie

    @Autowired
    private AccountRepository accountRepository;

    // METODI DI ACCESSO AL DATABASE \\

    // Restituisce la lista di tutti gli account
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    // Restituisce un container optional con un oggetto account
    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id);
    }

    // Restituisce l'oggetto account creato
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    // Restituisce l'oggetto account aggiornato
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    // Cancella oggetto account da db
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    // Cancella oggetto account da db in base ad id
    public void deleteById(Integer id) {
        Optional<Account> account = findById(id);

        if (account.isPresent()) {
            // Il get estra dall'optional il vero oggetto account che è poi quello da cancellare
            accountRepository.delete(account.get());
        }

    }

}
