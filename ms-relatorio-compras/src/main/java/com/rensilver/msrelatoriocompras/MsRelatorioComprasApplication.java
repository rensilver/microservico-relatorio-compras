package com.rensilver.msrelatoriocompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@SpringBootApplication
public class MsRelatorioComprasApplication {

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder
				.baseUrl("http://www.mocky.io/v2")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MsRelatorioComprasApplication.class, args);
	}

}
