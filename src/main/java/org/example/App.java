package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8081)
                .addService(new CoffeeServiceImpl())
                .build();

        server.start();

        System.out.println("Server started");

        server.awaitTermination();
    }

}
