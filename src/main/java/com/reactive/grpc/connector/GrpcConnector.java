package com.reactive.grpc.connector;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Andri Susanto
 */

@Component
public class GrpcConnector {

  @Bean
  public ManagedChannel channel(){
    return ManagedChannelBuilder
        .forAddress("localhost", 6565)
        .usePlaintext()
        .build();
  }
}
