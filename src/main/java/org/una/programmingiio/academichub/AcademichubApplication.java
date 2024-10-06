package org.una.programmingiio.academichub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.una.programmingiio.academichub.client.GreetingClient;
import reactor.core.publisher.Flux;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, R2dbcAutoConfiguration.class},scanBasePackages = {"org.una.programmingiio.academichub"})

public class AcademichubApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AcademichubApplication.class, args);
        GreetingClient greetingClient = context.getBean(GreetingClient.class);
        // Subscribe to the Mono to get the result asynchronously
        //greetingClient.getMessage().subscribe(message -> System.out.println(">> message = " + message));

        greetingClient.getMessages().subscribe(message -> System.out.println(">> message = " + message));
    }

}

