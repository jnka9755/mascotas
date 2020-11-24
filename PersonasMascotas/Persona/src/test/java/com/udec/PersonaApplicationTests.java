package com.udec;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class PersonaApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Test
	void contextLoads() {
		System.out.println("Resultado:   "+ bcrypt.encode("1234"));
		assertTrue(true);
	}

}
