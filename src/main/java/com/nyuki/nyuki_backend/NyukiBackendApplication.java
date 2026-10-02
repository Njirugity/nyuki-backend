package com.nyuki.nyuki_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NyukiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NyukiBackendApplication.class, args);
	}

}
