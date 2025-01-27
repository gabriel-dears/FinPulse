package com.fin_pulse.domain.market_data.repository;

import com.fin_pulse.domain.user.market_data.model.MarketData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketDataRepository extends JpaRepository<MarketData, Long> {
}
