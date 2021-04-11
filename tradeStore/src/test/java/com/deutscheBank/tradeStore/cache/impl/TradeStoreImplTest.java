package com.deutscheBank.tradeStore.cache.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.deutscheBank.tradeStore.model.Trade;


@WebMvcTest(TradeStoreImpl.class)
class TradeStoreImplTest {
	
	@Autowired
	TradeStoreImpl tradeStore;
	
	@BeforeEach
	public void setUp()
	{
		createDefaultCache();
	}
	
	
	@Test
	public void addTrade_whenTradeAdded_shouldincreaseSize()
	{
		//Assign
		Trade tradeToAdd = createTrade("T2",2,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		
		//Act
		boolean result =tradeStore.addTrade(tradeToAdd);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void getTradesById_whenGivenValidId_shoulReturnSetOfTradesWithOnlyGivenId()
	{
		//Act
		Set<Trade> selectedTrades = tradeStore.getTradesById("T1");
		
		//Assert
		for(Trade t:selectedTrades)
		{
			assertTrue(t.getTradeId().equals("T1"));
		}
	}
	
	private void createDefaultCache()
	{
		Trade trade1 = createTrade("T2",1,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T1",1,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		tradeStore.addTrade(trade1);
		tradeStore.addTrade(trade2);
	}

	private Trade createTrade(String tradeId,int version,String counterPartId,
			   String bookId,LocalDate maturityDate,LocalDate createdDate,String status)
	   {
		   Trade trade = new Trade();
		   trade.setTradeId(tradeId);
		   trade.setVersion(version);
		   trade.setCounterPartyId(counterPartId);
		   trade.setBookId(bookId);
		   trade.setMaturityDate(maturityDate);
		   trade.setCreatedDate(createdDate);
		   trade.setExpired(status);
		   
		   return trade;

	   }

}
