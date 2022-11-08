package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.server.ServerBean;
import org.example.service.implementation.CoffeeServiceImpl;
import org.example.template.CoffeeClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ServerBean.class);
        ctx.refresh();

        ServerBean serverBean = ctx.getBean(ServerBean.class);

        serverBean.start();
        System.out.println("Server started");
    }

}
