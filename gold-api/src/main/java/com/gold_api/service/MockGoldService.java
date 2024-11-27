package com.gold_api.service;

import gold.Gold;
import io.grpc.stub.StreamObserver;
import gold.GoldServiceGrpc;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MockGoldService extends GoldServiceGrpc.GoldServiceImplBase {

    @Override
    public void getGoldPrice(Gold.GoldPriceRequest request, StreamObserver<Gold.GoldPriceResponse> responseObserver) {
        String mockPrice = switch (request.getCurrency()) {
            case "USD" -> "1924.50";
            case "EUR" -> "1768.25";
            default -> "Unknown";
        };

        Gold.GoldPriceResponse response = Gold.GoldPriceResponse.newBuilder()
                .setPrice(mockPrice)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
