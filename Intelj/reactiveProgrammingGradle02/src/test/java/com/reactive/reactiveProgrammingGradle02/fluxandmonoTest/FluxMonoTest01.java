package com.reactive.reactiveProgrammingGradle02.fluxandmonoTest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


public class FluxMonoTest01 {


    @Test
    public void fluxTesting(){

    Flux<String> flux = Flux.just("Sprint","Spring02","loveSpring")
            //to attach error to the flux, Try
            .concatWith(Flux.error(new RuntimeException()))
            .concatWith(Flux.just("after Error"));
// to log the error
       // .log();

    // catch error here
    flux.subscribe(System.out::println,(e)->System.err.println("Exception error :: "+e));
    }


@Test
    public void FluxTesting01(){

        Flux<String> stringFlux = Flux.just("Sprint","Spring02","loveSpring").log();

        //to verify the steps i.e validating content in string flux
    //verifycomplete will start the step varification
        StepVerifier.create(stringFlux)
                .expectNext("Sprint")
                .expectNext("Spring02")

                .expectNext("loveSpring").verifyComplete();



      //When there is possible error at that time we can us verfiy()
    Flux<String> stringFlux02 = Flux.just("Sprint","Spring02","loveSpring")
            .concatWith(Flux.error(new RuntimeException()));

    StepVerifier.create(stringFlux02)
            .expectNext("Sprint")
            .expectNext("Spring02")
            .expectNext("loveSpring")
            .expectError(RuntimeException.class)
            .verify();



    }


}
