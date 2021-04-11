package com.deutscheBank.tradeStore.validator;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutscheBank.tradeStore.cache.impl.TradeStoreImpl;
import com.deutscheBank.tradeStore.exception.LowerVersionTradeException;
import com.deutscheBank.tradeStore.model.Trade;

@Service
public class TradeValidator 
{
	@Autowired
	private TradeStoreImpl tradeStore;
	
	public boolean isTradeValid(Trade trade) throws LowerVersionTradeException
	{
		if(!isTradeMatured(trade))
		{
			if(isTradeIdPresent(trade.getTradeId()))
			{
				if(!isLowerVersionTrade(trade))
					return true;
			}
			return true;
		}
		return false;
	}
		
	private boolean isTradeMatured(Trade trade)
	{
		LocalDate todayDate = LocalDate.now();
		return trade.getMaturityDate().isBefore(todayDate);
	}
	
	private boolean isLowerVersionTrade(Trade trade) throws LowerVersionTradeException
	{
		Set<Trade> tradesToCompare = tradeStore.getTradesById(trade.getTradeId());
		int maxVersion =0;
		for(Trade t : tradesToCompare)
		{
			if(maxVersion<t.getVersion())
				maxVersion = t.getVersion();
		}
		if(trade.getVersion()>=maxVersion)
			return true;
		throw new LowerVersionTradeException("Higher Version Trade already present");
	}
	
	private boolean isTradeIdPresent(String id)
	{
		return (tradeStore.getTradesById(id).size()>0);
	}
}
