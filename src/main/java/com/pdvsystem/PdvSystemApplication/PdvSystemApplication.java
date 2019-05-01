package com.pdvsystem.PdvSystemApplication;

import com.pdvsystem.PdvSystemApplication.services.PdvService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PdvSystemApplication {

	public static void main(final String[] args) {
		SpringApplication.run(PdvSystemApplication.class, args);
	}

	@Bean
	public PdvService pdvService() {
		return new PdvService();
	}

}
