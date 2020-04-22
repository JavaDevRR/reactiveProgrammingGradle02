package com.reactive.reactiveProgrammingGradle02.fluxandmonoTest;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoBackPressureTest {

@Test
public  void backPressure(){

    Flux<Integer> intFlux= Flux.range(1,5).log();

    StepVerifier.create(intFlux)
            .expectSubscription()
            .thenRequest(1)
            .expectNext(1)
            .thenRequest(1)
            .expectNext(2)
            .thenCancel()
            .verify();

}

/*@Test
    public void  backPressure02(){
    Flux<Integer> intFlux= Flux.range(1,5).log().
            intFlux.subscribe((ele) -> System.out.println(ele),
             (e) -> System.err.println("Exception :" +e)
                    , () ->  System.out.println("Done")
                    , (Subscription -> Subscription.request(2))

            );

    );*/





}
