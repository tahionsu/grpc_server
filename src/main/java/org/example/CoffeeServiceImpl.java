package org.example;

import com.example.grpc.CoffeeServiceGrpc;
import com.example.grpc.CoffeeServiceOuterClass;
import entity.CoffeeEntity;
import io.grpc.stub.StreamObserver;
import template.CoffeeClient;


public class CoffeeServiceImpl extends CoffeeServiceGrpc.CoffeeServiceImplBase {

    @Override
    public void getCoffee(CoffeeServiceOuterClass.CoffeeGetRequest request,
                          StreamObserver<CoffeeServiceOuterClass.CoffeeGetResponse> responseObserver) {

        CoffeeEntity coffee = CoffeeClient.getCoffee(request.getId(), "http://localhost:8080/coffee");

        CoffeeServiceOuterClass.CustomJSON description = new CoffeeServiceOuterClass.CustomJSON(
                coffee.getDescription().getScoreSCA(),
                coffee.getDescription().getGrind()
        );

        CoffeeServiceOuterClass.CoffeeGetResponse response = CoffeeServiceOuterClass
                .CoffeeGetResponse
                .newBuilder()
                .setId(coffee.getId())
                .setName(coffee.getName())
                .setDescription(description)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addCoffee(CoffeeServiceOuterClass.CoffeePostRequest request,
                          StreamObserver<CoffeeServiceOuterClass.CoffeePostResponse> responseObserver) {

        CoffeeEntity coffeeForPost = new CoffeeEntity();
        com.ntnv.gldva.json.CustomJSON json = new com.ntnv.gldva.json.CustomJSON();

        coffeeForPost.setName(request.getName());

        json.setScoreSCA(request.getDescription().getScoreSCA());
        json.setGrind(request.getDescription().getGrind());

        coffeeForPost.setDescription(json);

        String retCode = CoffeeClient.postCoffee(coffeeForPost, "http://localhost:8080/coffee");

        CoffeeServiceOuterClass.CoffeePostResponse response = CoffeeServiceOuterClass
                .CoffeePostResponse
                .newBuilder()
                .setId(retCode)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delCoffee(CoffeeServiceOuterClass.CoffeeDelRequest request,
                          StreamObserver<CoffeeServiceOuterClass.CoffeeDelResponse> responseObserver) {
        Integer retCod = CoffeeClient.deleteCoffee(request.getId(), "http://localhost:8080/coffee");

        CoffeeServiceOuterClass.CoffeeDelResponse responseDel = CoffeeServiceOuterClass
                .CoffeeDelResponse
                .newBuilder()
                .setRetCode(retCod)
                .build();

        responseObserver.onNext(responseDel);
        responseObserver.onCompleted();
    }
}

