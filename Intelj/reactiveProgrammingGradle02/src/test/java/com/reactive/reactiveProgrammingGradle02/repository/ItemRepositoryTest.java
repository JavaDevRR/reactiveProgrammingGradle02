package com.reactive.reactiveProgrammingGradle02.repository;


import com.reactive.reactiveProgrammingGradle02.document.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    List<Item> itmeList = Arrays.asList(new Item(null,"Dress",10.1)
                                        ,new Item(null,"Pant",15.0));




    @Before
    public void setUp(){

        List<Item> itmeList = Arrays.asList(new Item(null,"Dress",10.1)
                ,new Item(null,"Pant",15.0));


        // itemRepository.deleteAll().thenMany()

      /*  itemRepository.deleteAll().thenMany(Flux.fromIterable(itmeList))
                .flatMap(itemRepository::save).doOnNext(
                        (item) ->{
                            System.out.println();
                        });*/
    }

    @Test
    public void getAllItems() {
/*
        Iterable<Item> itmes = itemRepository.findAll();


        StepVerifier.create(itemRepository.findAll())
                .expectSubscription()
                .expectNextCount(0)
                .verifyComplete();
*/

    }


    @Test
    public void getItemByDesc(){

        StepVerifier.create(itemRepository.findByDescription("Dress")).expectSubscription().expectNextCount(1).verifyComplete();
    }

    @Test
    public void saveItem(){

        Item item = new Item(null,"tesst",180.0);
      //  StepVerifier.create(itemRepository.save(item)).expectSubscription().expectNextMatches(itme1 ->(itme1.getId()!=null && itme1.getDescription()!=null && itme1.getPrice())).verifyComplete();
    }

    @Test
    public void updateItem(){

      /*  Flux<Item> it = itemRepository.findByDescription("Dress").map((item) -> {
            return item.setPrice(120.9);
        }).flatMap((itme) -> {
            return itemRepository.save(itme);
        });*/

    }
}
