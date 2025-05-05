package org.trading_journal.example.trading_journal.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.trading_journal.example.trading_journal.Enum.tradeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



// Creo entità principale trades
@Entity
@Table( name = "trades")
public class Trade {
    
// Creo i campi dell'entità

// Colonna id chiave primaria
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

// instrument
private String instrument;

// entryDate
private LocalDate entryDate;

// exitDate
private LocalDate exitDate;

// entryPrice
private BigDecimal entryPrice;

// exitPrice
private BigDecimal exitPrice;

// positionType
private tradeType tradeType;

// size
private BigDecimal size;

// profitLoss
private BigDecimal profitLoss;

// notes
private String notes;

// strategy (relazione con entità Strategy) Many to Many
@ManyToMany
@JoinTable( name = "strategy_trade", joinColumns = @JoinColumn(name = "trade_id"), inverseJoinColumns= @JoinColumn(name = "strategy_id") )
private List<Strategy> strategies;

// account (relazione con entità Account) One to Many


}
