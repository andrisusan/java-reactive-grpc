package com.reactive.grpc;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Andri Susanto
 */

@RestController
public class CalculatorController {

  @Autowired
  private ManagedChannel channel;

  private ReactorCalculatorServiceGrpc.ReactorCalculatorServiceStub serviceStub;


  @GetMapping("/square/{value}")
  public Mono<Object> findSquare(@PathVariable String value) {
    serviceStub = ReactorCalculatorServiceGrpc.newReactorStub(channel);
    Input input = Input.newBuilder()
        .setNumber(Integer.parseInt(value))
        .build();

    return serviceStub.findSquare(input).map(Output::getResult);
  }

}
