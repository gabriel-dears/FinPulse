package com.fin_pulse.domain.instrument.model;

import com.fin_pulse.domain.user.market_data.model.MarketData;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Instruments", indexes = {
        @Index(name = "idx_symbol", columnList = "symbol")
})
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "symbol", nullable = false, unique = true, length = 10)
    private String symbol;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @OneToMany(mappedBy = "instrument", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MarketData> marketData;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<MarketData> getMarketData() {
        return marketData;
    }

    public void setMarketData(Set<MarketData> marketData) {
        this.marketData = marketData;
    }
}

