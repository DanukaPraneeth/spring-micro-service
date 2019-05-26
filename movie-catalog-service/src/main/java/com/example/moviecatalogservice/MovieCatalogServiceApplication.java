package com.example.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieCatalogServiceApplication {

	/*
		This bean method get executed only once
		method name don't matter, only the return type matters
		Any autowired Rest template is taken by this instance
	 */
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	@Bean
	public RestTemplate getRestTemplate2(){
		return new RestTemplate();
	}

	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

	public static void main(String[] args) {

		SpringApplication.run(MovieCatalogServiceApplication.class, args);



	}

}
