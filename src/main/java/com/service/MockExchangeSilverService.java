package com.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import shared.Exchange;
import shared.SilverServiceGrpc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@GrpcService
public class MockExchangeSilverService extends SilverServiceGrpc.SilverServiceImplBase {

    private final Random random = new Random();

    @Override
    public void getSilverRates(Exchange.EmptyRequest request, StreamObserver<Exchange.BankRatesResponse> responseObserver) {
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
                .addAllRates(bankRates)
                .build();

        // Send the response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private Exchange.BankRate createMockBankRate(String bankName) {

        float buyingRate = roundToTwoDecimals(32 + random.nextFloat() * 2); // Range: 32 to 34 inclusive
        float sellingRate = roundToTwoDecimals(buyingRate + 0.10f + random.nextFloat() * 0.40f); // Range: 0.10 to 0.50 higher

        return Exchange.BankRate.newBuilder()
                .setBankName(bankName)
                .setBuyingRate(buyingRate)
                .setSellingRate(sellingRate)
                .build();
    }

    private float roundToTwoDecimals(float value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP); // Round to 2 decimal places
        return bd.floatValue();
    }

}
