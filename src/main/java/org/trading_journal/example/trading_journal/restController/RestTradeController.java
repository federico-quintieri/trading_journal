package org.trading_journal.example.trading_journal.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trading_journal.example.trading_journal.model.Trade;
import org.trading_journal.example.trading_journal.service.TradeService;

@RestController

@RequestMapping("/api/trades")
public class RestTradeController {

    // Autowired dei service necessari

    @Autowired
    private TradeService tradeService;

    // Definisco i miei endpoint

    @GetMapping
    public ResponseEntity<List<Trade>> index() {

        List<Trade> trades = tradeService.findAllTrades();

        if (trades.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> show(@PathVariable Integer id) {

        Optional<Trade> trade = tradeService.findTradeById(id);

        if (trade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trade.get());

    }

}
