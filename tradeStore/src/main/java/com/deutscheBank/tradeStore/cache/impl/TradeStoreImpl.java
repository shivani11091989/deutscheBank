package com.deutscheBank.tradeStore.cache.impl;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.deutscheBank.tradeStore.cache.TradeStore;
import com.deutscheBank.tradeStore.model.Trade;

@Component
public class TradeStoreImpl implements TradeStore
{
	
  private Map<String,Set<Trade>> tradesCache = new TreeMap<>();
  private LocalDate lastStatusUpdateDate= LocalDate.MIN;

private TradeStoreImpl() {}
   
   public synchronized boolean addTrade(Trade t)
   {
	   Set<Trade> trades=tradesCache.getOrDefault(t.getTradeId(), new TreeSet<>());
	   t.setExpired("N");
	   trades.add(t);
	   tradesCache.put(t.getTradeId(), trades);
	   return true;
   }
   
   public synchronized Set<Trade> getTradesById(String id)
   {
	   Set<Trade> result = new TreeSet<>();
	   for(Trade t:tradesCache.get(id))
	   {
		   result.add(t.clone());
	   }
	   return result;   
   }
   
   public synchronized void updateTradeStatus()
   {
	   tradesCache.forEach((k,v)->v.forEach(trade->{
		   if(trade.getMaturityDate().isBefore(LocalDate.now()))
			{  
			   trade.setExpired("Y");
			}
	   }));
   }
   
   public Map<String,Set<Trade>> getAllTrades()
   {
	   Map<String,Set<Trade>> result = new TreeMap<>();
	   tradesCache.forEach((k,v)->{
		   Set<Trade> trades  = new TreeSet<>();
		   for(Trade t:v)
		   {
			   trades.add(t.clone());
		   }
		   result.put(k, trades);
	   });
	  return result;
   }
	   
   
   public LocalDate getLastStatusUpdateDate() 
   {
		return lastStatusUpdateDate;
   }
   
   public void setLastStatusUpdateDate(LocalDate date) 
   {
		 lastStatusUpdateDate = date;
   }

}
