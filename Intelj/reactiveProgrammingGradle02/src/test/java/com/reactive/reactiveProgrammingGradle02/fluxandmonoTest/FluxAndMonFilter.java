package com.reactive.reactiveProgrammingGradle02.fluxandmonoTest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static reactor.core.scheduler.Schedulers.parallel;

public class FluxAndMonFilter {

    List<String> stringList = Arrays.asList("test","atest01","etest02");



    //filter name starts with the "t" only
    @Test
    public void FluxFilter(){

        Flux<String> fluxString= Flux.fromIterable(stringList).filter(s->s.startsWith("t")).log();

        StepVerifier.create(fluxString).expectNext("test").verifyComplete();
    }


    //Transfor flux from one foarm to other foarm

    @Test
    public void TransforUsingMap(){
        Flux<String> fluxString= Flux.fromIterable(stringList).map(s ->s.toUpperCase()).log();


        Flux<Integer> fluxInt= Flux.fromIterable(stringList).map(s ->s.length());

        StepVerifier.create(fluxString).expectNext("TEST","ATEST01","ETEST02").verifyComplete();

    }

private List<String> convertToListM(String s){

    try {
        Thread.sleep(000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return Arrays.asList(s,"google");
}


    @Test
    public void TransforUsingFlatMap(){
        Flux<String> fluxString= Flux.fromIterable(Arrays.asList("test","atest01","etest02")).
                window(2)
                .flatMap((s)->s.map(this :: convertToListM).subscribeOn(parallel())).flatMap(s -> Flux.fromIterable(s)).log();

        StepVerifier.create(fluxString).expectNextCount(6).verifyComplete();

    }


    @Test
    public void TransforUsingFlatMap_maintain_Order(){
        Flux<String> fluxString= Flux.fromIterable(Arrays.asList("test","atest01","etest02")).
                window(2)
                .flatMapSequential((s)->s.map(this :: convertToListM).subscribeOn(parallel())).flatMap(s -> Flux.fromIterable(s)).log();

        StepVerifier.create(fluxString).expectNextCount(6).verifyComplete();

    }


    //merging flux

    @Test
    public void mergeTwoFlux(){

        Flux<String> flux1 = Flux.just("A","B","C");

        Flux<String> flux2 = Flux.just("X","Y","Z");

        Flux<String> fluxMerge = flux1.mergeWith(flux2);

       StepVerifier.create(fluxMerge.log()).expectNext("A","B","C","X","Y","Z").verifyComplete();


    }

    @Test
    public void mergeTwoFlux02(){

        Flux<String> flux1 = Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));

        Flux<String> flux2 = Flux.just("X","Y","Z").delayElements(Duration.ofSeconds(1));

        Flux<String> fluxMerge = flux1.mergeWith(flux2);

       // Flux<String> fluxMerge2 = flux1.mergeOrderedWith(flux2);

        StepVerifier.create(fluxMerge.log()).expectNext("A","B","C","X","Y","Z").verifyComplete();


    }


}
