package com.reactive.reactiveProgrammingGradle02.fluxandmonoTest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.util.Arrays;
import java.util.List;



public class FluxErrorHandling {

    List<String> stringList = Arrays.asList("test","atest01","etest02");


    @Test
    private void errorHandling01(){

        Flux<String> stringFlux = Flux.just("A","B","C").log()
                .concatWith(Flux.error(new RuntimeException()))
                .concatWith(Flux.just("D").onErrorResume(
                        (e) ->{System.out.println("EXCEPTION :: "+e);
                        return Flux.just("Default");
                        }
                        ));

        StepVerifier.create(stringFlux).expectSubscription().
                expectNext("A","B","C").expectNext("Default").verifyComplete();
               // .expectError(RuntimeException.class)

    }
    @Test
    private void errorHandling02(){

        Flux<String> stringFlux = Flux.just("A","B","C").log()
                .concatWith(Flux.error(new RuntimeException()))
                .concatWith(Flux.just("D").onErrorReturn("Default" ));

        StepVerifier.create(stringFlux).expectSubscription().
                expectNext("A","B","C").expectNext("Default").verifyComplete();
        // .expectError(RuntimeException.class)

    }

    @Test
    private void errorHandling03(){

        Flux<String> stringFlux = Flux.just("A","B","C").log()
                .concatWith(Flux.error(new RuntimeException()));
           //     .concatWith(Flux.just("D").onErrorMap((e)-> new CustException(e)));

        StepVerifier.create(stringFlux).expectSubscription().
                expectNext("A","B","C").expectNext("Default").verifyComplete();
        // .expectError(RuntimeException.class)

    }
























}
