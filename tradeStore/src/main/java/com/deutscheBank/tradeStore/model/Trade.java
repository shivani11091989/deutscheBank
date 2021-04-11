package com.deutscheBank.tradeStore.model;

import java.time.LocalDate;

public class Trade implements Comparable<Trade>
{
	private String tradeId;
	private int version;
	private String counterPartyId;
	private String bookId;
	private LocalDate maturityDate;
	private LocalDate createdDate;
	private String isExpired;
	
	public String getTradeId() 
	{
		return tradeId;
	}
	
	public void setTradeId(String tradeId) 
	{
		this.tradeId = tradeId;
	}
	
	public int getVersion()
	{
		return version;
	}
	
	public void setVersion(int version) 
	{
		this.version = version;
	}
	
	public String getCounterPartyId() 
	{
		return counterPartyId;
	}
	
	public void setCounterPartyId(String counterPartyId) 
	{
		this.counterPartyId = counterPartyId;
	}
	
	public String getBookId() 
	{
		return bookId;
	}
	
	public void setBookId(String bookId) 
	{
		this.bookId = bookId;
	}
	
	public LocalDate getMaturityDate() 
	{
		return maturityDate;
	}
	
	public void setMaturityDate(LocalDate maturityDate) 
	{
		this.maturityDate = maturityDate;
	}
	
	public LocalDate getCreatedDate() 
	{
		return createdDate;
	}
	
	public void setCreatedDate(LocalDate createdDate) 
	{
		this.createdDate = createdDate;
	}
	
	public String isExpired()
	{
		return isExpired;
	}
	
	public void setExpired(String isExpired) 
	{
		this.isExpired = isExpired;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (tradeId == null) {
			if (other.tradeId != null)
				return false;
		} else if (!tradeId.equals(other.tradeId))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public int compareTo(Trade o) {
		if(this.version<o.version)
			return 1;
		else if(this.version>o.version)
			return -1;
		return 0;
	}
	
	public Trade clone()
	{
		Trade clone = new Trade();
		clone.setBookId(this.getBookId());
		clone.setTradeId(this.getTradeId());
		clone.setCounterPartyId(this.counterPartyId);
		clone.setCreatedDate(this.getCreatedDate());
		clone.setMaturityDate(this.getMaturityDate());
		clone.setExpired(this.isExpired);
		clone.setVersion(this.getVersion());
		return clone;
	}

}
