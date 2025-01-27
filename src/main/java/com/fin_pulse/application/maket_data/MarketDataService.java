package com.fin_pulse.application.maket_data;

import com.fin_pulse.domain.market_data.repository.MarketDataRepository;
import com.fin_pulse.domain.user.market_data.model.MarketData;
import com.fin_pulse.infrastructure.market_data.api.MarketDataApiClient;
import org.springframework.stereotype.Service;

@Service
public class MarketDataService {

    private final MarketDataApiClient apiClient;
    private final MarketDataRepository repository;

    public MarketDataService(MarketDataApiClient apiClient, MarketDataRepository repository) {
        this.apiClient = apiClient;
        this.repository = repository;
    }

    public MarketData fetchAndSaveMarketData(String symbol) {
        String data = apiClient.fetchMarketData(symbol);
        // Parse and save to the database
        MarketData marketData = parseMarketData(data);
        repository.save(marketData);
        return marketData;
    }

    private MarketData parseMarketData(String data) {
        // Convert JSON response into a MarketData object
        return new MarketData();  // Example, implement parsing
    }
}

