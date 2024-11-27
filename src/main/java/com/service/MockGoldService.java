package com.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import shared.Exchange;
import shared.GoldServiceGrpc;

@GrpcService
public class MockGoldService extends GoldServiceGrpc.GoldServiceImplBase {

    @Override
    public void getGoldPrice(Exchange.GoldPriceRequest request, StreamObserver<Exchange.GoldPriceResponse> responseObserver) {
        String mockPrice = switch (request.getCurrency()) {
            case "USD" -> "1924.50";
            case "EUR" -> "1768.25";
            default -> "Unknown";
        };

        Exchange.GoldPriceResponse response = Exchange.GoldPriceResponse.newBuilder()
                .setPrice(mockPrice)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
