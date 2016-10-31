package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoggingTestApplication {
	
	private static final String RESPONSE = "Ahoy hoy!";
	
	private static final Logger LOG = LoggerFactory.getLogger("com.example");

	@RequestMapping("/success")
	public String happyPath() {
		MDC.put("test-mdc-key", "test-mdc-value");
		LOG.trace("Processing success request for '{}'", "/success");
		LOG.debug("Processing success request for '{}'", "/success");
		LOG.info("Processing success request for '{}'", "/success");
		return RESPONSE;
	}
	
	@RequestMapping("/exception")
	public String exceptionPath() {
		MDC.put("test-mdc-key", "test-mdc-value");
		LOG.trace("Processing success request for '{}'", "/exception");
		LOG.debug("Processing success request for '{}'", "/exception");
		LOG.info("Processing error request for '{}'", "/exception");
		LOG.warn("Processing error request for '{}'", "/exception");
		LOG.error("Exception occurred.", new RuntimeException("Crap happens"));
		return RESPONSE;
	}

	public static void main(String[] args) {
		SpringApplication.run(LoggingTestApplication.class, args);
	}
	
}
