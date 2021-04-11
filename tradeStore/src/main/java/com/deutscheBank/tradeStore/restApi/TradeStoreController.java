package com.deutscheBank.tradeStore.restApi;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deutscheBank.tradeStore.exception.LowerVersionTradeException;
import com.deutscheBank.tradeStore.model.Trade;
import com.deutscheBank.tradeStore.service.TradeService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/tradeStore")
public class TradeStoreController
{
	@Autowired
	TradeService tradeService;
	
	@PostMapping(
	        value = "/addTrade",
	        consumes = {MediaType.APPLICATION_JSON_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<Trade> postTrade(@RequestBody Trade trade) throws LowerVersionTradeException {
	        Trade tradeSaved = tradeService.saveTrade(trade);
	        return ResponseEntity
	            .created(URI
	                     .create(String.format("/addTrade/%s", tradeSaved.getTradeId())))
	            .body(tradeSaved);
	    }

}
