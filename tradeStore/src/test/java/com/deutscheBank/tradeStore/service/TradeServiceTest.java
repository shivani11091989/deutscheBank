package com.deutscheBank.tradeStore.service;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.deutscheBank.tradeStore.cache.TradeStore;
import com.deutscheBank.tradeStore.exception.LowerVersionTradeException;
import com.deutscheBank.tradeStore.model.Trade;
import com.deutscheBank.tradeStore.validator.TradeValidator;


@WebMvcTest(TradeService.class)
class TradeServiceTest 
{

	@MockBean
	TradeValidator tradeValidator;
	@MockBean
	TradeStore tradeStore;
	
	@Autowired
	TradeService tradeService;
	
	@BeforeEach
	public void setup()
	{
		when(tradeStore.getLastStatusUpdateDate()).thenReturn(LocalDate.MIN);
	}
	
	@Test
	public void saveTrade_whenGivenValidCalled_shouldAddTrade() throws LowerVersionTradeException
	{
		//Assign
		Trade t = new Trade();
		when(tradeValidator.isTradeValid(t)).thenReturn(true);
		
		//Act
		tradeService.saveTrade(new Trade());
		
		//Assert
		verify(tradeStore,times(1)).addTrade(t);
	}
	
	@Test
	public void saveTrade_whenGivenInValidCalled_shouldnotAddTrade() throws LowerVersionTradeException
	{
		//Assign
		Trade t = new Trade();
		when(tradeValidator.isTradeValid(t)).thenReturn(false);
		
		//Act
		tradeService.saveTrade(new Trade());
		
		//Assert
		verify(tradeStore,times(0)).addTrade(t);
	}

}
