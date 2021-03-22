package com.reactive.grpc.server;

import com.reactive.grpc.Input;
import com.reactive.grpc.Output;
import com.reactive.grpc.ReactorCalculatorServiceGrpc.CalculatorServiceImplBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Andri Susanto
 */

public class CalculatorService extends CalculatorServiceImplBase {

  private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);

  @Override
  public Mono<Output> findSquare(Mono<Input> request) {
    LOGGER.info("findSquare Ini request nya: {}", request);
    return request.map(Input::getNumber)
        .map(i -> Output.newBuilder().setResult(i * i).build());
  }

  @Override
  public Flux<Output> findFactors(Mono<Input> request) {
    LOGGER.info("findFactors Ini request nya: {}", request);
    return request.map(Input::getNumber)
        .filter(i -> i > 0)
        .flatMapMany(input -> Flux.range(2, input / 2)
            .filter(f -> input % f == 0))
        .map(o -> Output.newBuilder().setResult(o).build());
  }

  @Override
  public Mono<Output> findSum(Flux<Input> request) {
    LOGGER.info("findSum Ini request nya: {}", request);
    return request
        .map(Input::getNumber)
        .reduce(Integer::sum)
        .map(i -> Output.newBuilder().setResult(i).build());
  }
}
