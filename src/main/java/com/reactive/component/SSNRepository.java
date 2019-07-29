package com.reactive.component;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface SSNRepository{ 
	Mono<SSNDetails> getSSNById(int id);

	Flux<SSNDetails> fetchAll();

	Mono<Void> saveSSN(Mono<SSNDetails> user);
	
	public void create(SSNDetails user) ;
}
