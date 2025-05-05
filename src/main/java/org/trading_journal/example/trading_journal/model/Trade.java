package org.trading_journal.example.trading_journal.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.trading_journal.example.trading_journal.Enum.tradeType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

// Creo entità principale trades
@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il campo 'instrument' non può essere vuoto.")
    @Length(max = 100, message = "Il nome dello strumento non può superare i 100 caratteri.")
    private String instrument;

    @NotNull(message = "La data di ingresso è obbligatoria.")
    @PastOrPresent(message = "La data di ingresso non può essere nel futuro.")
    private LocalDate entryDate;

    @NotNull(message = "La data di uscita è obbligatoria.")
    @PastOrPresent(message = "La data di uscita non può essere nel futuro.")
    private LocalDate exitDate;

    @NotNull(message = "Il prezzo di ingresso è obbligatorio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo di ingresso deve essere positivo.")
    private BigDecimal entryPrice;

    @NotNull(message = "Il prezzo di uscita è obbligatorio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo di uscita deve essere positivo.")
    private BigDecimal exitPrice;

    @NotNull(message = "La tipologia di posizione (LONG o SHORT) è obbligatoria.")
    @Enumerated(EnumType.STRING)
    private tradeType tradeType;

    @NotNull(message = "La size della posizione è obbligatoria.")
    @DecimalMin(value = "0.01", message = "La size deve essere maggiore di zero.")
    private BigDecimal size;

    @NotNull(message = "Il profit/loss è obbligatorio.")
    private BigDecimal profitLoss;

    @Size(max = 1000, message = "Le note non possono superare i 1000 caratteri.")
    private String notes;

    @ManyToMany
    @JoinTable(name = "strategy_trade", joinColumns = @JoinColumn(name = "trade_id"), inverseJoinColumns = @JoinColumn(name = "strategy_id"))
    private List<Strategy> strategies;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @NotNull(message = "L'account associato è obbligatorio.")
    private Account account;

}
