package com.reactive.component;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SSNHandler {
	
	@Autowired
	private SSNRepository ssnRepository;

	public Mono<ServerResponse> helloWorld(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
			.body(BodyInserters.fromObject("Hello World!"));
	}
	
	public Flux<SSNDetails> listSSN(ServerRequest request) {
		Flux<SSNDetails> ssns = ssnRepository.fetchAll();
//		return ServerResponse.ok().contentType(APPLICATION_JSON).body(user, SSNDetails.class);
		return ssns;
	}

	public Mono<ServerResponse> getSSNDetails(ServerRequest request) {
		int userId = Integer.valueOf(request.pathVariable("id"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<SSNDetails> userMono = ssnRepository.getSSNById(userId);
		return userMono.flatMap(user -> ServerResponse.ok()
				.contentType(APPLICATION_JSON)
				.body(BodyInserters.fromObject(user)))
				.switchIfEmpty(notFound);
	}
}
