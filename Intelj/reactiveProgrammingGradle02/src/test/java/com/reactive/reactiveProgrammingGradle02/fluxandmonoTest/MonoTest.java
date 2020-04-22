package com.reactive.reactiveProgrammingGradle02.fluxandmonoTest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

@Test
    public void Test01(){

  Mono<String> stringMono = Mono.just("welcome");

    StepVerifier.create(stringMono)
    .expectNext("welcome").verifyComplete();

}



}
