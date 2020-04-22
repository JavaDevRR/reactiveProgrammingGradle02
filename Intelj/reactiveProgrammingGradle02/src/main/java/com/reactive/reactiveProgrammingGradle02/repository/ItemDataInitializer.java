package com.reactive.reactiveProgrammingGradle02.repository;

import com.reactive.reactiveProgrammingGradle02.document.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Component
public class ItemDataInitializer implements CommandLineRunner {


    @Autowired
    ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        initalDataSetup();
    }

    public List<Item> data() {

        return Arrays.asList(new Item(null, "Samsung TV", 399.99),
                new Item(null, "LG TV", 329.99),
                new Item(null, "Apple Watch", 349.99),
                new Item("ABC", "Beats HeadPhones", 19.99));
    }

    private void initalDataSetup() {

        itemRepository.deleteAll()
                .thenMany(Flux.fromIterable(data()).flatMap(itemRepository::save).thenMany(itemRepository.findAll())).subscribe(
                (item) -> {System.out.println("item Inserted successfully");});

    }
}
