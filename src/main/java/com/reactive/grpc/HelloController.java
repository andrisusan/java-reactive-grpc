package com.reactive.grpc;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Andri Susanto
 */

@RestController
public class HelloController {

  @Autowired
  private ManagedChannel channel;

  private ReactorCalculatorServiceGrpc.ReactorCalculatorServiceStub serviceStub;


  @GetMapping("/hello")
  public Mono<Object> hello() {
    serviceStub = ReactorCalculatorServiceGrpc.newReactorStub(channel);
    Input input = Input.newBuilder()
        .setNumber(5)
        .build();

    return serviceStub.findSquare(input).map(Output::getResult);
  }

}
