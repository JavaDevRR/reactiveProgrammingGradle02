package com.reactive.reactiveProgrammingGradle02.controller;

import com.reactive.reactiveProgrammingGradle02.document.Item;
import com.reactive.reactiveProgrammingGradle02.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.time.Duration;

import static com.reactive.reactiveProgrammingGradle02.Constants.ItemConstant.ITEM_URL;

@RestController
public class FluxMonoController {


    @Autowired
   public ItemRepository itemRepository;


    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping(value = "/getFlux")
    public Flux<Integer> returnFlux(){

        return Flux.just(1,2,3,4,5).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value = "/getFluxStream" , produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> returnFluxStream(){

        return Flux.just(1,2,3,4,5).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value =ITEM_URL )
    public Flux<Item> getAllItems (){

   return null; //(Flux<Item>) itemRepository.findAll();
    }

}
