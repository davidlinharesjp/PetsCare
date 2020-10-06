package br.com.petsCare;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSpringDataWebSupport
@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class PetsCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsCareApplication.class, args);
	}

}
