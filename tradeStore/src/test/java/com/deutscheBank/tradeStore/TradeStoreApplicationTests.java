package com.deutscheBank.tradeStore;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deutscheBank.tradeStore.restApi.TradeStoreController;

@SpringBootTest
class TradeStoreApplicationTests {
	
	@Autowired
	TradeStoreController controller;

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

}
