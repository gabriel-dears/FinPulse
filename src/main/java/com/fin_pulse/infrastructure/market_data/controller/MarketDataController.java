package com.fin_pulse.infrastructure.market_data.controller;

import com.fin_pulse.application.maket_data.MarketDataService;
import com.fin_pulse.domain.user.market_data.model.MarketData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market-data")
public class MarketDataController {

    private final MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<MarketData> getMarketData(@PathVariable String symbol) {
        MarketData data = marketDataService.fetchAndSaveMarketData(symbol);
        return ResponseEntity.ok(data);
    }

}

