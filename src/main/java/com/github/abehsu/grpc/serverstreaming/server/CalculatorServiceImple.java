package com.github.abehsu.grpc.serverstreaming.server;

import com.proto.primenumber.CalculatorServiceGrpc;
import com.proto.primenumber.PrimeNumberDecompositionRequest;
import com.proto.primenumber.PrimeNumberDecompositionResponse;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImple extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void primeNumberDecomposition(PrimeNumberDecompositionRequest request, StreamObserver<PrimeNumberDecompositionResponse> responseObserver) {

        Integer number = request.getNumber();
        Integer divisor = 2;

        try {
            while (number > 1){
                if ( number % divisor == 0){
                    PrimeNumberDecompositionResponse response= PrimeNumberDecompositionResponse.newBuilder()
                            .setResult(divisor)
                            .build();
                    responseObserver.onNext(response);
                    number = number / divisor;
                }
                else{
                    divisor = divisor + 1;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            responseObserver.onCompleted();
        }

        //        super.primeNumberDecomposition(request, responseObserver);
    }
}
