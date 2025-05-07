package org.trading_journal.example.trading_journal.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trading_journal.example.trading_journal.model.Account;
import org.trading_journal.example.trading_journal.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class RestAccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> index() {

        List<Account> accounts = accountService.findAllAccounts();

        if (accounts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(accounts);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> show(@PathVariable Integer id) {

        Optional<Account> account = accountService.findById(id);

        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(account.get());
    }

}
