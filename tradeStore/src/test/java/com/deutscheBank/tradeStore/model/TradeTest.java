package com.deutscheBank.tradeStore.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest(Trade.class)
class TradeTest {
	@Test
	public void equals_whenComparedWithNull_shouldReturnFalse()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = null;
		
		//Act
		boolean result = trade1.equals(trade2);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void equals_whenSameObject_shouldReturnTrue()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = trade1;
		
		//Act
		boolean result = trade1.equals(trade2);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void equals_whenComparedWithDifferenrClassObject_shouldReturnFalse()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		String trade2 = "Trade";
		
		//Act
		boolean result = trade1.equals(trade2);
		
		//Assert
		assertFalse(result);
	}

	@Test
	public void equals_whenTradeIdAndVersionSame_shouldReturnEqual()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T1",1,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		
		//Act
		boolean result = trade1.equals(trade2);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void equals_whenTradeIddifferent_shouldReturnNotEqual()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T2",1,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		
		//Act
		boolean result = trade1.equals(trade2);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void equals_whenTradeVersionDifferent_shouldReturnNotEqual()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T1",2,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		
		//Act
		boolean result = trade1.equals(trade2);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void hashcode_whenTradeIdAndVersionSame_shouldReturnSameHashCode()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T1",1,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		
		//Assert
		assertTrue(trade1.hashCode()==trade2.hashCode());
	}
	
	@Test
	public void hashcode_whenTradeIddifferent_shouldReturnDifferentHashCode()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T2",1,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");

		//Assert
		assertFalse(trade1.hashCode()==trade2.hashCode());
	}
	
	@Test
	public void hashcode_whenTradeVersionDifferent_shouldReturnDifferentHashCode()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T1",2,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");	
		//Assert
		assertFalse(trade1.hashCode()==trade2.hashCode());
	}
	
	@Test
	public void compareTo_whenTrade1VersionEqualsTrade2Version_shouldReturnZero()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T1",1,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");	
		//Assert
		assertTrue(trade1.compareTo(trade2)==0);
	}
	
	@Test
	public void compareTo_whenTrade1VersionGreaterThenTrade2Version_shouldReturnNegative()
	{
		//Assign
		Trade trade1 = createTrade("T1",2,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T1",1,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");	
		//Assert
		assertTrue(trade1.compareTo(trade2)<0);
	}
	
	@Test
	public void compareTo_whenTrade1VersionlessThanTrade2Version_shouldReturnPositive()
	{
		//Assign
		Trade trade1 = createTrade("T1",1,"CP-2","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");
		Trade trade2 = createTrade("T1",2,"CP-1","B1",
				   LocalDate.of(2021, 5, 23),LocalDate.of(2021, 4, 01),"N");	
		//Assert
		assertTrue(trade1.compareTo(trade2)>0);
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
