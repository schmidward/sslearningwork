package com.ericdschmid.SecurityBackendApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true) //NOT RECOMMENDED FOR PRODUCTION
public class SecurityBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityBackendApplication.class, args);
	}

}
