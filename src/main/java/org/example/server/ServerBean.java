package org.example.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.service.implementation.CoffeeServiceImpl;
import org.example.template.CoffeeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class ServerBean {

    private RestTemplate restTemplate;
    private CoffeeClient coffeeClient;
    private Server server;

    public ServerBean() {
        this.restTemplate = new RestTemplate();
        this.coffeeClient = new CoffeeClient(restTemplate);
        this.server = ServerBuilder.forPort(8081)
                .addService(new CoffeeServiceImpl(coffeeClient))
                .build();
    }

    public void start() throws IOException {
        this.server.start();
    }


    public void await() throws InterruptedException {
        this.server.awaitTermination();
    }

}
