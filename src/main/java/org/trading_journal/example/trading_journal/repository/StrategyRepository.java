package org.trading_journal.example.trading_journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trading_journal.example.trading_journal.model.Strategy;

public interface StrategyRepository extends JpaRepository<Strategy, Integer> {

}