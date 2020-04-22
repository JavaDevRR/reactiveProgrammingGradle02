package com.reactive.reactiveProgrammingGradle02.fluxandmonoTest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FluxAndMonoFactoryTest {

   List<String> stringList = Arrays.asList("test","test01","test02");


   //create the flux from list
    @Test
    public void FluxTesting(){


        //To create flux from list
        Flux<String> nameFlux =Flux.fromIterable(stringList);

        StepVerifier.create(nameFlux).expectNext("test","test01","test02").verifyComplete();
    }

    //create the flux from string
    @Test
    public void createFluxUsingStringArray(){

        String [] stringArray=  new String[] {"covid19","2020","CNN","Fox"};

        Flux<String> fluxArray = Flux.fromArray(stringArray);

        StepVerifier.create(fluxArray).expectNext("covid19","2020","CNN","Fox").verifyComplete();

    }

    //create flux from stream (JAVA 8 API)

    @Test
    public void createFluxUsingStream(){



       Stream<String> stringStrm= stringList.stream();

       Flux<String> fluxFromStream= Flux.fromStream(stringStrm).log();

        StepVerifier.create(fluxFromStream).expectNext("test","test01","test02").verifyComplete();
    }


    @Test
    public void CreateEmptyMono(){

      Mono<String> emptyMono = Mono.justOrEmpty("");
      StepVerifier.create(emptyMono.log()).verifyComplete();

    }

    @Test
    public void createMonoUsingSupplier(){

        Supplier<String> stringSupplier = () -> "string Supplier";

        Mono<String > stringMono = Mono.fromSupplier(stringSupplier).log();

        StepVerifier.create(stringMono).expectNext("string Supplier").verifyComplete();
    }


    @Test
    public void  fluxRangeMethod(){

        Flux<Integer> fluxInt =Flux.range(1,5);

        StepVerifier.create(fluxInt).expectNext(1,2,3,4,5).verifyComplete();
    }

}
