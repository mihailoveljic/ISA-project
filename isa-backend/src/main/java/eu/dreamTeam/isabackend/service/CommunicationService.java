package eu.dreamTeam.isabackend.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import  eu.dreamTeam.grpc.MessageProto;
import  eu.dreamTeam.grpc.MessageResponseProto;
import  eu.dreamTeam.grpc.SpringGrpcServiceGrpc;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@GrpcService
public class CommunicationService extends SpringGrpcServiceGrpc.SpringGrpcServiceImplBase {
    private final BloodSampleService bloodSampleService;

    @Autowired
    public CommunicationService(BloodSampleService bloodSampleService){
        this.bloodSampleService = bloodSampleService;
    }
    @Override
    public void communicate(MessageProto request, StreamObserver<MessageResponseProto> responseObserver) {
        MessageResponseProto responseMessage;
        if(bloodSampleService.getBloodSampleForPurchase(request.getBloodType(), request.getAmount()))
            responseMessage = MessageResponseProto.newBuilder().setBloodType(request.getBloodType()).setAmount(request.getAmount()).build();
        else
            responseMessage = MessageResponseProto.newBuilder().setBloodType(request.getBloodType()).setAmount(0).build();
        responseObserver.onNext(responseMessage);
        responseObserver.onCompleted();
    }
}
