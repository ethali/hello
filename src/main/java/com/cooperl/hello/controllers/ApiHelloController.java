package com.cooperl.hello.controllers;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.cooperl.hello.contracts.ApiHello;
import com.cooperl.hello.models.Hello;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ApiHelloController implements ApiHello {


    private final Map<String, String> hellos;

    public ApiHelloController() {
        this.hellos = Map.of("kitty","Hello Kitty", "world", "Hello World");
    }


    @Override
    public Flux<Hello> getHellos() {
        return Flux.fromIterable(
            hellos.entrySet().stream()
                .map(entry -> Hello.builder().code(entry.getKey()).name(entry.getValue()).build())
                .collect(Collectors.toList())
        );
    }

    @Override
    public Mono<Hello> getHello(String codeHello) {
        if (hellos.containsKey(codeHello)) {
            return Mono.just(Hello.builder().code(codeHello).name(hellos.get(codeHello)).build());
        }
        else {
            return Mono.empty();
        }
    }

}
