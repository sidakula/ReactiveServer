package com.reactive.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class SSNClient {

	public static void main(String args[]) {
		WebClient client = WebClient.create("http://localhost:8081");

		Mono<ClientResponse> result = client.get()
				.uri("/helloWorld")
				.accept(MediaType.TEXT_PLAIN)
				.exchange();

			System.out.println("Result = " + result.flatMap(res -> res.bodyToMono(String.class)).block());
	}
	
}
