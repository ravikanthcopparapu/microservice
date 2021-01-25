package com.myproject.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	Logger log=LoggerFactory.getLogger(CurrencyConversionController.class);
	@Autowired
	private Environment env;
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion caluclateCurrency(@PathVariable String from,@PathVariable String to, @PathVariable BigDecimal quantity ){
		String port=env.getProperty("local.server.port");
		RestTemplate template=new RestTemplate();
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("from", from);
		map.put("to",to);
		ResponseEntity<CurrencyConversion> entity=template.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,map);
		CurrencyConversion conv=(CurrencyConversion) entity.getBody();
		return new CurrencyConversion(10001L,from,to,quantity,conv.getConversionMultiple(),quantity.multiply(conv.getConversionMultiple()),port);
	}
		
	@GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion caluclateCurrencyfeign(@PathVariable String from,@PathVariable String to, @PathVariable BigDecimal quantity ){
		String port=env.getProperty("local.server.port");
		CurrencyConversion conv=(CurrencyConversion) proxy.getExchangeRate(from, to);
		log.info("{}",conv);
		return new CurrencyConversion(10001L,from,to,quantity,conv.getConversionMultiple(),quantity.multiply(conv.getConversionMultiple()),conv.getEnvironment());
	}

}
