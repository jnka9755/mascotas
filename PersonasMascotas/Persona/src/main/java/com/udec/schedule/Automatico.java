package com.udec.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Automatico {

	@Scheduled(fixedDelay = 5000)
	public void metodo1() {
		
		System.out.println("Jenkins!");
	}
}
