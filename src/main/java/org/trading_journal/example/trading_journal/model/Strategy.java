package org.trading_journal.example.trading_journal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "strategies")
public class Strategy {

    //--- Campi della mia entità ---\\

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome della strategia è obbligatorio.")
    @Size(max = 100, message = "Il nome non può superare i 100 caratteri.")
    private String name;

    @NotBlank(message = "La descrizione della strategia è obbligatoria")
    @Size(max = 1000, message = "La descrizione non può superare i 1000 caratteri.")
    private String description;

    // Relazione Many to Many con trades
    // Una strategia può essere associata a più trade
    @ManyToMany(mappedBy = "strategies")
    @JsonIgnore
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Trade> getTrades() {
        return this.trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

}