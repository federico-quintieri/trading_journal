package org.trading_journal.example.trading_journal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    // --- Campi della mia entità ---\\

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome dell'account è obbligatorio.")
    @Size(max = 100, message = "Il nome dell'account non può superare i 100 caratteri.")
    private String name;

    @NotBlank(message = "Il nome del broker è obbligatorio.")
    @Size(max = 100, message = "Il nome del broker non può superare i 100 caratteri.")
    private String broker;

    @NotBlank(message = "La valuta è obbligatoria.")
    @Size(min = 3, max = 3, message = "La valuta deve essere un codice ISO a 3 lettere (es. EUR, USD).")
    private String currency;

    @NotNull(message = "Il saldo iniziale è obbligatorio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Il saldo iniziale deve essere positivo.")
    private BigDecimal initialBalance;

    // Relazione one to many con entità trade
    // Un account può avere più trade e un Trade deve avere per forza un account
    // associato
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trade> trades;

    // --- Genero i getters e i setters ---\\

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBroker() {
        return this.broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getInitialBalance() {
        return this.initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public List<Trade> getTrades() {
        return this.trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

}
