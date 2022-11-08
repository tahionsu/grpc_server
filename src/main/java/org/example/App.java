package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.service.implementation.CoffeeServiceImpl;
import org.example.template.CoffeeClient;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        CoffeeClient coffeeClient = new CoffeeClient(restTemplate);
        Server server = ServerBuilder.forPort(8081)
                .addService(new CoffeeServiceImpl(coffeeClient))
                .build();

        server.start();

        System.out.println("Server started");

        server.awaitTermination();
    }

}
