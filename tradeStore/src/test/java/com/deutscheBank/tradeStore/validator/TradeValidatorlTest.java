package com.deutscheBank.tradeStore.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.deutscheBank.tradeStore.cache.impl.TradeStoreImpl;
import com.deutscheBank.tradeStore.exception.LowerVersionTradeException;
import com.deutscheBank.tradeStore.model.Trade;

@WebMvcTest(TradeValidator.class)
class TradeValidatorlTest 
{

   @MockBean
   private TradeStoreImpl tradeStore;
      
   @Autowired
   private TradeValidator tradeValidator;
   
   @Test
   public void isTradeValid_onValidTrade_shouldReturnTrue() throws LowerVersionTradeException
   {
	   //Assign
	   Set<Trade> trades = createDefaultTrades();
	   when(tradeStore.getTradesById("T1")).thenReturn(trades);
	   Trade trade = createTrade("T1",3,"CP-1","B1",
			   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
	   
	    //Act
	   boolean result = tradeValidator.isTradeValid(trade);
	   
	   //assert
	   assertTrue(result);
   }
   
   @Test
   public void isTradeValid_onExpiredMaturity_shouldReturnFalse() throws LowerVersionTradeException
   {
	   //Assign
	   Set<Trade> trades = createDefaultTrades();
	   when(tradeStore.getTradesById("T1")).thenReturn(trades);
	   Trade expiredTrade = createTrade("T1",3,"CP-1","B1",
			   LocalDate.of(2021, 4, 01),LocalDate.of(2021, 01, 01),"Y");
	   
	    //Act
	   boolean result = tradeValidator.isTradeValid(expiredTrade);
	   
	   //assert
	   assertFalse(result);
   }
   
   @Test
   public void isTradeValid_onLowerVersion_shouldThrowException() throws LowerVersionTradeException
   {
	   //Assign
	   Set<Trade> trades = createDefaultTrades();
	   when(tradeStore.getTradesById("T1")).thenReturn(trades);
	   Trade lowerVersionTrade = createTrade("T1",1,"CP-1","B1",
			   LocalDate.of(2021, 5, 01),LocalDate.of(2021, 01, 01),"N");
	   	   
	   //Act-assert
	   Exception exception = assertThrows(LowerVersionTradeException.class, () -> {
		   tradeValidator.isTradeValid(lowerVersionTrade);
	    });
	   
	   String expectedMessage = "Higher Version Trade already present";
	   String actualMessage = exception.getMessage();

	   assertTrue(actualMessage.contains(expectedMessage));
   }
   
   @Test
   public void isTradeValid_onNewTradeWithValidMaturity_shouldReturnTrue() throws LowerVersionTradeException
   {
	   //Assign
	   when(tradeStore.getTradesById("T2")).thenReturn(new TreeSet<>());
	   Trade trade = createTrade("T2",1,"CP-1","B1",
			   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
	   
	    //Act
	   boolean result = tradeValidator.isTradeValid(trade);
	   
	   //assert
	   assertTrue(result);
   }
   
   
   private Set<Trade> createDefaultTrades()
   {
	   Set<Trade> trades =  new TreeSet<>();
	   
	   Trade trade1 = new Trade();
	   trade1.setTradeId("T1");
	   trade1.setVersion(1);
	   trade1.setCounterPartyId("CP-1");
	   trade1.setBookId("B1");
	   trade1.setMaturityDate(LocalDate.of(2021, 5, 23));
	   trade1.setCreatedDate(LocalDate.of(2021, 4, 01));
	   trade1.setExpired("N");
	   trades.add(trade1);
	   
	   Trade trade2 = new Trade();
	   trade2.setTradeId("T1");
	   trade2.setVersion(2);
	   trade2.setCounterPartyId("CP-2");
	   trade2.setBookId("B2");
	   trade2.setMaturityDate(LocalDate.of(2021, 5, 23));
	   trade2.setCreatedDate(LocalDate.of(2021, 4, 01));
	   trade2.setExpired("N");
	   trades.add(trade2);	 
	   
	   return trades;
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
