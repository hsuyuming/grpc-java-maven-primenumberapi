package com.github.abehsu.grpc.serverstreaming.client;

import com.proto.primenumber.CalculatorServiceGrpc;
import com.proto.primenumber.PrimeNumberDecompositionRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PrimeNumberClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50052)
                .usePlaintext()
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        Integer number = 345234733;
        PrimeNumberDecompositionRequest request = PrimeNumberDecompositionRequest.newBuilder()
                .setNumber(number)
                .build();

        stub.primeNumberDecomposition(request).forEachRemaining(primeNumberDecompositionResponse -> {
            System.out.println(primeNumberDecompositionResponse.getResult());
        });


        //do something
        System.out.println("Shutting down channel");
        channel.shutdown();



    }
}
