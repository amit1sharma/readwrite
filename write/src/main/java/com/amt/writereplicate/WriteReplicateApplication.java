package com.amt.writereplicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
public class WriteReplicateApplication {

	public static void main(String[] args) {
		SpringApplication.run(WriteReplicateApplication.class, args);
	}

}
