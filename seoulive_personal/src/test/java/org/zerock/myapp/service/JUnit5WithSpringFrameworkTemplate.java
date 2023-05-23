package org.zerock.myapp.service;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/**/root-*.xml",
	"file:src/main/webapp/WEB-INF/**/servlet-*.xml"
})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUnit5WithSpringFrameworkTemplate {

	@BeforeAll void beforeAll() {log.trace("beforeAll() invoked.");}
	@BeforeEach void beforeEach() {log.trace("beforeEach() invoked.");}
	@AfterEach void afterEach() {log.trace("afterEach() invoked.");}
	@AfterAll void afterAll() {log.trace("afterAll() invoked.");}
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("contextLoads")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void contextLoads() {
		log.trace("contextLoads() invoked.");
		
	} // contextLoads
	
	
} // end class
