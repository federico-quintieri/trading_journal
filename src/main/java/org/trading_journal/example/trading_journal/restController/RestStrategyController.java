package org.trading_journal.example.trading_journal.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trading_journal.example.trading_journal.model.Strategy;
import org.trading_journal.example.trading_journal.service.StrategyService;

@RestController
@RequestMapping("/api/strategies")
public class RestStrategyController {

    @Autowired
    private StrategyService strategyService;

    @GetMapping
    public ResponseEntity<List<Strategy>> index() {

        List<Strategy> strategies = strategyService.findAllStrategies();

        if (strategies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(strategies);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Strategy> show(@PathVariable Integer id) {

        Optional<Strategy> strategy = strategyService.findByIdStrategy(id);

        if (strategy.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(strategy.get());

    }
}
