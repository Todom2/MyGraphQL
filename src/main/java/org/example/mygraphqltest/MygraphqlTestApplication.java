package org.example.mygraphqltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories
public class MygraphqlTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MygraphqlTestApplication.class, args);
	}

}
