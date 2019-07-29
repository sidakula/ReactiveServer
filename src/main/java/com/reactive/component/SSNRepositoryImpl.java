package com.reactive.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SSNRepositoryImpl implements SSNRepository{
	Map<Integer, SSNDetails> ssnMap = new ConcurrentHashMap<Integer, SSNDetails>(); 
    public SSNRepositoryImpl(){
        ssnMap.put(1, new SSNDetails("888929292", "Robert", "Ludlum", "rl@rl.com"));
        ssnMap.put(2, new SSNDetails("777828282", "John", "Grisham", "jg@jg.com"));
        ssnMap.put(3, new SSNDetails("686958695", "James", "Patterson", "jp@jp.com"));
    }

    @Override
    public Mono<SSNDetails> getSSNById(int id) {
        return Mono.justOrEmpty(ssnMap.get(id));
    }

    @Override
    public Flux<SSNDetails> fetchAll() {
        return Flux.fromStream(ssnMap.values().stream());
    }

    @Override
    public Mono<Void> saveSSN(Mono<SSNDetails> user) {    
        Mono<SSNDetails> userMono = user.doOnNext(value->{
            ssnMap.put((ssnMap.keySet().size() +1), value);            
        } );
        return userMono.then();
    } 
    
    
    @Override
    public void create(SSNDetails user) {    
            ssnMap.put(ssnMap.size()+1, user);            
    } 
}
