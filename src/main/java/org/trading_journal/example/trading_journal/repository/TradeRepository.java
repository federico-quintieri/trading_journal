package org.trading_journal.example.trading_journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trading_journal.example.trading_journal.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {

}