package org.example.service.implementation;

import com.example.grpc.CoffeeServiceGrpc;
import com.example.grpc.CoffeeServiceOuterClass;
import com.google.protobuf.InvalidProtocolBufferException;
import org.example.entity.CoffeeEntity;
import io.grpc.stub.StreamObserver;
import org.example.template.CoffeeClient;
import org.example.json.CustomJSON;


public class CoffeeServiceImpl extends CoffeeServiceGrpc.CoffeeServiceImplBase {

    CoffeeClient coffeeClient;

    public CoffeeServiceImpl(CoffeeClient coffeeClient) {
        this.coffeeClient = coffeeClient;
    }

    @Override
    public void getCoffee(CoffeeServiceOuterClass.CoffeeGetRequest request,
                          StreamObserver<CoffeeServiceOuterClass.CoffeeGetResponse> responseObserver) throws InvalidProtocolBufferException {

        CoffeeServiceOuterClass.CoffeeGetResponse response = null;
        CoffeeEntity coffee = coffeeClient.getCoffee(request.getId(), "http://localhost:8080/coffee");

        if (coffee != null) {
            CoffeeServiceOuterClass.CustomJSON description = new CoffeeServiceOuterClass.CustomJSON(
                    coffee.getDescription().getScoreSCA(),
                    coffee.getDescription().getGrind()
            );

            response = CoffeeServiceOuterClass
                    .CoffeeGetResponse
                    .newBuilder()
                    .setId(coffee.getId())
                    .setName(coffee.getName())
                    .setDescription(description)
                    .build();

        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void addCoffee(CoffeeServiceOuterClass.CoffeePostRequest request,
                          StreamObserver<CoffeeServiceOuterClass.CoffeePostResponse> responseObserver) {

        CoffeeEntity coffeeForPost = new CoffeeEntity();
        CustomJSON json = new CustomJSON();

        coffeeForPost.setName(request.getName());

        json.setScoreSCA(request.getDescription().getScoreSCA());
        json.setGrind(request.getDescription().getGrind());

        coffeeForPost.setDescription(json);

        String retCode = coffeeClient.postCoffee(coffeeForPost, "http://localhost:8080/coffee");

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

        Integer retCod = coffeeClient.deleteCoffee(request.getId(), "http://localhost:8080/coffee");

        CoffeeServiceOuterClass.CoffeeDelResponse responseDel = CoffeeServiceOuterClass
                .CoffeeDelResponse
                .newBuilder()
                .setRetCode(retCod)
                .build();

        responseObserver.onNext(responseDel);
        responseObserver.onCompleted();
    }
}

