package com.deutscheBank.tradeStore.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutscheBank.tradeStore.cache.TradeStore;
import com.deutscheBank.tradeStore.exception.LowerVersionTradeException;
import com.deutscheBank.tradeStore.model.Trade;
import com.deutscheBank.tradeStore.validator.TradeValidator;

@Service
public class TradeService 
{
	@Autowired
	TradeValidator tradeValidator;
	@Autowired
	TradeStore tradeStore;
	
	public TradeService()
	{
		startTradeStatusUpdateService();
	}
	
	public Trade saveTrade(Trade trade) throws LowerVersionTradeException
	{
		if(tradeValidator.isTradeValid(trade))
		{
			tradeStore.addTrade(trade);
		}	
		return trade;
	}
	
	private void startTradeStatusUpdateService()
	{
		Thread thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				LocalDate todayDate = LocalDate.now();
				while (true)
				{	
					if(tradeStore!=null && tradeStore.getLastStatusUpdateDate()!=null
							&&tradeStore.getLastStatusUpdateDate().isBefore(todayDate))
					{
						tradeStore.updateTradeStatus();
						tradeStore.setLastStatusUpdateDate(todayDate);
					}
					
				}

			}
		});
		thread.setName("UpdateTradeState");
		thread.start();
	}
}