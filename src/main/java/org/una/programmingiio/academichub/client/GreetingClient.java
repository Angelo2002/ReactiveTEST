package org.una.programmingiio.academichub.client;

import org.una.programmingiio.academichub.dto.GreetingDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GreetingClient {

    private final WebClient client;

    // Spring Boot autoconfigures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public GreetingClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getMessage() {
        System.out.printf(">> Invoking GET /hello%n");
        return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GreetingDto.class)
                .map(GreetingDto::getMessage);
    }

    public Flux<String> getMessages() {
        System.out.printf(">> Invoking GET /greetings%n");
        return this.client.get().uri("/greetings").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(GreetingDto.class)
                .map(GreetingDto::getMessage);
    }

}