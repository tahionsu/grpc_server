package org.example;

import org.example.server.ServerBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ServerBean.class);
        ctx.refresh();

        ServerBean serverBean = ctx.getBean(ServerBean.class);

        serverBean.start();
        serverBean.await();
    }

}
