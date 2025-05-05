package org.trading_journal.example.trading_journal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trading_journal.example.trading_journal.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}