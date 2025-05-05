package org.trading_journal.example.trading_journal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trading_journal.example.trading_journal.model.Strategy;
import org.trading_journal.example.trading_journal.model.Trade;
import org.trading_journal.example.trading_journal.repository.StrategyRepository;

@Service
public class StrategyService {

    // Autowired repo necessarie
    @Autowired
    private StrategyRepository strategyRepository;

    // METODI DI ACCESSO AL DATABASE \\

    // Metodo che ritorna una lista di tutte le strategie nel db
    public List<Strategy> findAllStrategies() {
        return strategyRepository.findAll();
    }

    // Metodo che ritorna un optional se trova strategia in base ad id
    public Optional<Strategy> findByIdStrategy(Integer id) {
        return strategyRepository.findById(id);
    }

    // Metodo che crea una strategia nel DB
    public Strategy create(Strategy strategy) {
        return strategyRepository.save(strategy);
    }

    // Metodo che aggiorna una strategia nel DB
    public Strategy update(Strategy strategy) {
        return strategyRepository.save(strategy);
    }

    // Metodo che cancella una strategia dal DB
    public void delete(Strategy strategy) {
        strategyRepository.delete(strategy);
    }

    // Metodo che cancella una strategia dal DB in base ad ID
    public void deleteById(Integer id) {
        Optional<Strategy> strategy = findByIdStrategy(id);

        // Se ho trovato la strategia da cancellare
        if (strategy.isPresent()) {
            // Prima la cancello nei trade che la contengono
            for (Trade linkedTrades : strategy.get().getTrades()) {
                // Per ogni trade seleziono le strategie collegate e cancello la strategia che
                // voglio eliminare dal DB
                // Così nessun trade avrà una strategia che non esiste più nel DB
                linkedTrades.getStrategies().remove(strategy.get());
            }

            // Poi cancello la strategia stessa
            strategyRepository.delete(strategy.get());
        }
    }
}
