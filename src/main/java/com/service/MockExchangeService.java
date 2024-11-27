package com.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import shared.Exchange;
import shared.ExchangeServiceGrpc;

import java.util.List;
import java.util.Random;

@GrpcService
public class MockExchangeService extends ExchangeServiceGrpc.ExchangeServiceImplBase {

    private final Random random = new Random();

    @Override
    public void getBankRates(Exchange.EmptyRequest request, StreamObserver<Exchange.BankRatesResponse> responseObserver) {
        // Mock data for 10 banks
        List<Exchange.BankRate> bankRates = List.of(
                createMockBankRate("Bank A"),
                createMockBankRate("Bank B"),
                createMockBankRate("Bank C"),
                createMockBankRate("Bank D"),
                createMockBankRate("Bank E"),
                createMockBankRate("Bank F"),
                createMockBankRate("Bank G"),
                createMockBankRate("Bank H"),
                createMockBankRate("Bank I"),
                createMockBankRate("Bank J")
        );

        // Build the response
        Exchange.BankRatesResponse response = Exchange.BankRatesResponse.newBuilder()
                .addAllRates(bankRates) // Add all rates to the response
                .build();

        // Send the response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private Exchange.BankRate createMockBankRate(String bankName) {
        float buyingRate = 2950 + random.nextFloat() * 90;
        float sellingRate = buyingRate + 10;
        return Exchange.BankRate.newBuilder()
                .setBankName(bankName)
                .setGoldBuyingRate(buyingRate)
                .setGoldSellingRate(sellingRate)
                .build();
    }

}