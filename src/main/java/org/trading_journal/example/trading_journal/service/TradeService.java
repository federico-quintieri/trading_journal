package org.trading_journal.example.trading_journal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trading_journal.example.trading_journal.model.Trade;
import org.trading_journal.example.trading_journal.repository.TradeRepository;

@Service
public class TradeService {

    // Autowired delle repo necessarie
    @Autowired
    private TradeRepository tradeRepository;

    // Metodi che interagiscono con il DB
    public List<Trade> findAllTrades() {
        return tradeRepository.findAll();
    }

    public Optional<Trade> findTradeById(Integer Id) {
        return tradeRepository.findById(Id);
    }

    public Trade create(Trade trade) {
        return tradeRepository.save(trade);
    }

    public Trade update(Trade trade) {
        return tradeRepository.save(trade);
    }

    public void delete(Trade trade) {
        tradeRepository.delete(trade);
    }

    public void deleteById(Integer id) {
        Optional<Trade> trade = findTradeById(id);

        if (trade.isPresent()) {
            tradeRepository.delete(trade.get());
        }
    }

}
