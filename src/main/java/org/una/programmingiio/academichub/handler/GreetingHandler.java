package org.una.programmingiio.academichub.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.una.programmingiio.academichub.dto.GreetingDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        System.out.println(">> Invoking GET /hello, with request = " + request);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new GreetingDto("Hello, Spring!")));
    }


    public Mono<ServerResponse> greetings(ServerRequest request) {
        System.out.println(">> Invoking GET /greetings, with request = " + request);
        Flux<GreetingDto> greetingsFlux = Flux.just(
                new GreetingDto("Hello 1, Spring!"),
                new GreetingDto("Hello 2, Spring!"),
                new GreetingDto("Hello 3, Spring!"),
                new GreetingDto("Hello 4, Spring!"),
                new GreetingDto("Hello 5, Spring!")
        );

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(greetingsFlux, GreetingDto.class);
    }
}