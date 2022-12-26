package com.example.victoralvesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.victoralvesapi.config.property.AlgamoneyApiProperty;

@EnableConfigurationProperties(value = AlgamoneyApiProperty.class)
@SpringBootApplication
public class AlgamoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgamoneyApiApplication.class, args);
	}

}
