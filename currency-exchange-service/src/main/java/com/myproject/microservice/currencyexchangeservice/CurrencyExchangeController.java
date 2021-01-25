package com.myproject.microservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	Logger log=LoggerFactory.getLogger(CurrencyExchangeController.class);
@Autowired
private Environment environment;
@Autowired
private CurrencyExchangeRepository repo;
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	private CurrencyExchange getExchangeRate(@PathVariable String from,@PathVariable String to) {
		log.info("retriving exchange parameters from {} to {}",from,to);
		CurrencyExchange exchange=repo.findByFromAndTo(from, to);
		if(exchange==null) {
			throw new RuntimeException("Unable to identify Exchange Rate" +from+ "and to" +to);
		}
		String port=environment.getProperty("local.server.port");
		log.info("Get exchange value {}",port);
		exchange.setEnvironment(port);
		return exchange;
		
	}
}
