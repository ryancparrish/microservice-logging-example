package com.example;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoggingTestApplicationTests {

	@Test
	public void success_should_return_HelloWorld() {

		when().
        	get("/success").
        then().
        	statusCode(200).
        	body(equalTo("Ahoy hoy!"));

	}

	@Test
	public void exception_should_return_HelloWorld() {

		when().
        	get("/exception").
        then().
        	statusCode(200).
        	body(equalTo("Ahoy hoy!"));

	}

}
