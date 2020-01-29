package com.github.abehsu.grpc.serverstreaming.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class PrimeNumberServer {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Start gRPC server");

        Server server = ServerBuilder.forPort(50052)
                .addService(new CalculatorServiceImple())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("prepare shutdown server");
            server.shutdown();
            System.out.println("Successfully shutdown server");
        }));

        server.awaitTermination();


    }
}
