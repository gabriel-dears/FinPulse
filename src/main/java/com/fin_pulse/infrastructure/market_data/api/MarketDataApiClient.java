package com.fin_pulse.infrastructure.market_data.api;

import org.springframework.stereotype.Service;

@Service
public class MarketDataApiClient {

    private final WebClient webClient;

    public MarketDataApiClient() {
        this.webClient = WebClient.create("https://api.marketdata.com");
    }

    public String fetchMarketData(String symbol) {
        return webClient.get()
                .uri("/price?symbol={symbol}", symbol)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

