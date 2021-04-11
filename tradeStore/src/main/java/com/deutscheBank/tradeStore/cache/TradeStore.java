package com.deutscheBank.tradeStore.cache;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import com.deutscheBank.tradeStore.model.Trade;

public interface TradeStore 
{
	 boolean addTrade(Trade t);
	 Set<Trade> getTradesById(String id);
	 void updateTradeStatus();
	 Map<String,Set<Trade>> getAllTrades();
	 LocalDate getLastStatusUpdateDate();
	 void setLastStatusUpdateDate(LocalDate date) ;
}
