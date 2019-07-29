package com.reactive.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/")
public class SSNController {

	@Autowired
	private SSNHandler service;
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello");
    }
    
	@GetMapping(value = "/ssn")
	Flux<SSNDetails> flights(){
		return Flux.from(service.listSSN(null));
//		return ResponseEntity.status(HttpStatus.OK).body(Flux.from(service.listSSN(null)));

	}

//	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/ssn/create")
//	@ResponseBody
//	public void aircraft(@RequestBody SSNDetails ssnDetails){
//		 service.create(ssnDetails);
//
//	}
//
//	@GetMapping("/")
//	Mono<String> home() {
//		return Mono.just("flights");
//	}
}
